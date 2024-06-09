package com.example.projetmobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.projetmobile.api.CountryApiService
import com.example.projetmobile.api.RetrofitClient
import com.example.projetmobile.model.Country
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val searchEditText = findViewById<EditText>(R.id.search_edit_text)
        val searchButton = findViewById<Button>(R.id.search_button)
        val resultTextView = findViewById<TextView>(R.id.result_text_view)

        searchButton.setOnClickListener {
            val query = searchEditText.text.toString()
            if (query.isNotEmpty()) {
                searchCountry(query, resultTextView)
            }
        }
    }

    private fun searchCountry(query: String, resultTextView: TextView) {
        val apiService = RetrofitClient.instance.create(CountryApiService::class.java)
        apiService.getCountriesByName(query).enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                if (response.isSuccessful && response.body() != null) {
                    val countries = response.body()
                    val result = StringBuilder()
                    countries?.forEach { country ->
                        result.append("${country.name} : ${country.flag}\n")
                    }
                    resultTextView.text = result.toString()
                } else {
                    resultTextView.text = "Aucun résultat trouvé."
                }
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                resultTextView.text = "Erreur lors de la recherche."
            }
        })
    }
}
