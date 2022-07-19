package com.example.kormo00

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Loginpage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginpage)

        val userloginpage = findViewById<Button>(R.id.employerbutton)
        userloginpage.setOnClickListener {
            val shift = Intent(this,UserLogin::class.java)
            startActivity(shift)
        }
        val workerloginpage = findViewById<Button>(R.id.employeebutton)
        workerloginpage.setOnClickListener {
            val shift = Intent(this,WorkerLogin::class.java)
            startActivity(shift)
        }
    }
}