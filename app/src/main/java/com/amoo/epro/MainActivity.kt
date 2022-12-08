package com.amoo.epro

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.amoo.epro.adapters.CategoryAdapter
import com.amoo.epro.adapters.MainAdapter
import com.amoo.epro.databinding.ActivityMainBinding
import com.amoo.epro.models.CategoryCard
import com.amoo.epro.models.MainCard
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var list: ArrayList<MainCard>
    private lateinit var categoryList: ArrayList<CategoryCard>
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.mainTool)
        supportActionBar?.title = "Shopify"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        toggle = ActionBarDrawerToggle(
            this@MainActivity, binding.drawerLayout, binding.mainTool,
            R.string.open, R.string.close
        )

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // MainCard Recyclerview
        getData()

        // Category RecyclerView
        categoryData()

        // Setting NavBar Item Click Listener
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.feedback -> {
                    Toast.makeText(this, "You click on ${it.title}", Toast.LENGTH_LONG).show()
                    binding.drawerLayout.closeDrawers()

                }
                R.id.privacy -> {
                    Toast.makeText(this, "You click on ${it.title}", Toast.LENGTH_LONG).show()
                    binding.drawerLayout.closeDrawers()

                }
                R.id.terrms -> {
                    Toast.makeText(this, "You click on ${it.title}", Toast.LENGTH_LONG).show()
                    binding.drawerLayout.closeDrawers()

                }
                R.id.exit -> {
                    Toast.makeText(this, "You click on ${it.title}", Toast.LENGTH_LONG).show()
                    binding.drawerLayout.closeDrawers()

                }


            }
            true
        }


    }

    // Make request to get data from firestore database
    private fun getData() {
        list = ArrayList()
        db = FirebaseFirestore.getInstance()
        db.collection("main_card")
            .get().addOnSuccessListener { task ->
                if (!task.isEmpty) {
                    val cardList = task.documents
                    for (d: DocumentSnapshot in cardList) {
                        val data: MainCard? = d.toObject(MainCard::class.java)
                        list.add(data!!)
                        list.reverse()
                    }

                } else {
                    Toast.makeText(this, "No data found in Database", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }


        binding.mainCard.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        val adapter = MainAdapter(list,this@MainActivity)
        binding.mainCard.adapter = adapter

    }

    private fun categoryData() {
        categoryList = ArrayList()
        categoryList.add(CategoryCard(R.drawable.maincardsamplepic,"Shoes"))
        categoryList.add(CategoryCard(R.drawable.maincardsamplepic,"Clothes"))
        categoryList.add(CategoryCard(R.drawable.maincardsamplepic,"Watches"))
        categoryList.add(CategoryCard(R.drawable.maincardsamplepic,"Electronics"))

        binding.categoryCard.layoutManager = GridLayoutManager(this@MainActivity,2)
        val adapter = CategoryAdapter(categoryList)
        binding.categoryCard.adapter = adapter
    }


}
