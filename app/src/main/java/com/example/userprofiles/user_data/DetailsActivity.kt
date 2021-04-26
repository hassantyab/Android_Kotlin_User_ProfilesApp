package com.example.userprofiles.user_data


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.userprofiles.R

class DetailsActivity : AppCompatActivity() {


    private lateinit var  fname:TextView
    private lateinit var  lname:TextView
    private lateinit var  email:TextView
    private lateinit var  avatar:ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cmp_profile_view)
        this.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        intializeItem()
        setInfo()
    }

    private fun intializeItem() {
        fname= findViewById(R.id.first_name)
        lname= findViewById(R.id.last_name)
        email= findViewById(R.id.email_addr)
        avatar= findViewById(R.id.user_imageView)
    }


    private fun setInfo() {
        fname.text = intent.getStringExtra("fname")
        lname.text = intent.getStringExtra("lname")
        email.text = intent.getStringExtra("email")
        Glide.with(this)
            .load(intent.getStringExtra("avatar"))
            .into(avatar)
    }
}