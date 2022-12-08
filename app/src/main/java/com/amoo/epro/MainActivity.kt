package com.amoo.epro

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.amoo.epro.adapters.MainAdapter
import com.amoo.epro.databinding.ActivityMainBinding
import com.amoo.epro.models.MainCard
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var toggle : ActionBarDrawerToggle
    private lateinit var list : ArrayList<MainCard>
    private lateinit var adapter : MainAdapter
    private lateinit var db : FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.mainTool)
        supportActionBar?.title = "Shopify"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        toggle = ActionBarDrawerToggle(this@MainActivity,binding.drawerLayout,binding.mainTool,
        R.string.open,R.string.close)

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        getData()





        // Setting NavBar Item Click Listener
        binding.navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.feedback -> {
                    Toast.makeText(this,"You click on ${it.title}",Toast.LENGTH_LONG).show()
                    binding.drawerLayout.closeDrawers()

                }
                R.id.privacy -> {
                    Toast.makeText(this,"You click on ${it.title}",Toast.LENGTH_LONG).show()
                    binding.drawerLayout.closeDrawers()

                }
                R.id.terrms -> {
                    Toast.makeText(this,"You click on ${it.title}",Toast.LENGTH_LONG).show()
                    binding.drawerLayout.closeDrawers()

                }
                R.id.exit -> {
                    Toast.makeText(this,"You click on ${it.title}",Toast.LENGTH_LONG).show()
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
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val url = document.data.getValue("url").toString()
                    val price = document.data.getValue("price") as Int
                    val category = document.data.getValue("category").toString()
                    val title = document.data.getValue("title").toString()
                    val description = document.data.getValue("description").toString()
                    Log.d(TAG, "$title => $description")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }



     }



}