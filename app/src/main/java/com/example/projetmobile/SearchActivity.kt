package com.example.projetmobile

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetmobile.api.CountryApiService
import com.example.projetmobile.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var countryAdapter: CountryAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var reloadButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val backButton = findViewById<ImageButton>(R.id.back_button)
        backButton.setOnClickListener {
            onBackPressed()
        }

        searchEditText = findViewById(R.id.search_edit_text)
        searchButton = findViewById(R.id.search_button)
        reloadButton = findViewById(R.id.reload_button)
        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        searchButton.setOnClickListener {
            val query = searchEditText.text.toString()
            if (query.isNotEmpty()) {
                searchCountries(query)
            }
        }

        reloadButton.setOnClickListener {
            val query = searchEditText.text.toString()
            if (query.isNotEmpty()) {
                searchCountries(query)
            }
        }
    }

    private fun searchCountries(query: String) {
        progressBar.visibility = View.VISIBLE
        reloadButton.visibility = View.GONE
        val apiService = RetrofitClient.instance.create(CountryApiService::class.java)
        apiService.getCountriesByName(query).enqueue(object : Callback<List<Country>> {
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
