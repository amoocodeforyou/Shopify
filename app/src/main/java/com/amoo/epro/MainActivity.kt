package com.amoo.epro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.amoo.epro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var toggle : ActionBarDrawerToggle
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



}