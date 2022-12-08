package com.amoo.epro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.amoo.epro.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent
        setSupportActionBar(binding.detailTool)
        supportActionBar?.title = data.getStringExtra("category")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val url = data.getStringExtra("url")
        Picasso.get().load(url).into(binding.productImage)

        binding.title.text = data.getStringExtra("title")
        binding.description.text = data.getStringExtra("description")

        binding.buyNow.setOnClickListener {
            Toast.makeText(this@DetailActivity,"Purchased Successful",Toast.LENGTH_LONG).show()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.cart){
            Toast.makeText(this@DetailActivity,"Cart is empty",Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}