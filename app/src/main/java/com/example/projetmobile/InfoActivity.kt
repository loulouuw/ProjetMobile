package com.example.projetmobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val infoTextView = findViewById<TextView>(R.id.textView3)
        // Remplacer cette ligne par le code pour récupérer les infos du jour de la BDD ou de l'API
        infoTextView.text = "Informations du jour..."
    }
}
