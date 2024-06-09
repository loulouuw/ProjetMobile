package com.example.projetmobile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.projetmobile.api.RetrofitClient
import com.example.projetmobile.model.Country
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_country)

        val searchButton = findViewById<Button>(R.id.search_button)
        val searchEditText = findViewById<EditText>(R.id.search_edit_text)
        val resultTextView = findViewById<TextView>(R.id.result_text_view)

        searchButton.setOnClickListener {
            val countryName = searchEditText.text.toString()
            searchCountry(countryName, resultTextView)
        }
    }

    private fun searchCountry(name: String, resultTextView: TextView) {
        RetrofitClient.instance.getCountryByName(name).enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                if (response.isSuccessful && response.body() != null) {
                    val country = response.body()!![0]
                    val displayText = "Name: ${country.name.common}\nCapital: ${country.capital?.joinToString()}\nFlag: ${country.flags.png}"
                    resultTextView.text = displayText
                } else {
                    resultTextView.text = "Country not found"
                }
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                resultTextView.text = "Error: ${t.message}"
            }
        })
    }
}
