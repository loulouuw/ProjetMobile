package com.example.projetmobile

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CountryAdapter(private val countries: List<Country>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val countryName: TextView = itemView.findViewById(R.id.countryName)
        val countryFlag: ImageView = itemView.findViewById(R.id.countryFlag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.countryName.text = country.name.common
        Glide.with(holder.itemView.context).load(country.flags.png).into(holder.countryFlag)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, CountryDetailActivity::class.java).apply {
                putExtra("countryName", country.name.common)
                putExtra("countryFlag", country.flags.png)
                putExtra("countryDetails", "Capitale: ${country.capital?.joinToString(", ")}\n" +
                        "Région: ${country.region}\n" +
                        "Sous-région: ${country.subregion}\n" +
                        "Population: ${country.population}\n" +
                        "Frontières: ${country.borders?.joinToString(", ")}")
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = countries.size
}
