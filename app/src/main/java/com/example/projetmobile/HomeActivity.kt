package com.example.projetmobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetmobile.api.CountryApiService
import com.example.projetmobile.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var countryAdapter: CountryAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var reloadButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val searchButton = findViewById<Button>(R.id.search1)
        val infoButton = findViewById<Button>(R.id.infos)
        val exitButton = findViewById<Button>(R.id.exit)
        reloadButton = findViewById(R.id.reload_button)
        progressBar = findViewById(R.id.progressBar)

        searchButton.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        infoButton.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }

        exitButton.setOnClickListener {
            finish()
        }

        reloadButton.setOnClickListener {
            fetchCountries()
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchCountries()
    }

    private fun fetchCountries() {
        progressBar.visibility = View.VISIBLE
        reloadButton.visibility = View.GONE
        val apiService = RetrofitClient.instance.create(CountryApiService::class.java)
        apiService.getAllCountries().enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                progressBar.visibility = View.GONE
                if (response.isSuccessful && response.body() != null) {
                    countryAdapter = CountryAdapter(response.body()!!)
                    recyclerView.adapter = countryAdapter
                } else {
                    reloadButton.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                progressBar.visibility = View.GONE
                reloadButton.visibility = View.VISIBLE
            }
        })
    }
}
