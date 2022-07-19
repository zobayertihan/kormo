package com.example.kormo00

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginpagebutton = findViewById<Button>(R.id.loginpage)
        loginpagebutton.setOnClickListener {
            val shift = Intent(this,Loginpage::class.java)
            startActivity(shift)
        }
        val aboutkormobutton = findViewById<Button>(R.id.aboutkormo)
        aboutkormobutton.setOnClickListener {
            val shift = Intent(this,AboutUs::class.java)
            startActivity(shift)
        }
    }
}