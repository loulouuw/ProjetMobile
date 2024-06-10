package com.example.projetmobile

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import com.bumptech.glide.Glide

class CountryDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        val backButton = findViewById<ImageButton>(R.id.back_button)
        backButton.setOnClickListener {
            onBackPressed()
        }

        val countryName = findViewById<TextView>(R.id.country_name)
        val countryFlag = findViewById<ImageView>(R.id.country_flag)
        val countryDetails = findViewById<TextView>(R.id.country_details)

        val name = intent.getStringExtra("countryName")
        val flag = intent.getStringExtra("countryFlag")
        val details = intent.getStringExtra("countryDetails")

        countryName.text = name
        Glide.with(this).load(flag).into(countryFlag)
        countryDetails.text = details
    }
}
