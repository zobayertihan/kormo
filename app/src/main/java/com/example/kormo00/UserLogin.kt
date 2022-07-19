package com.example.kormo00

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.kormo00.databinding.ActivityUserLoginBinding
import com.example.kormo00.databinding.ActivityUsersignupBinding
import com.google.firebase.auth.FirebaseAuth

class UserLogin : AppCompatActivity() {

    private lateinit var binding: ActivityUserLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.userlogin.setOnClickListener {
            val email = binding.loginusernameuser.text.toString()
            val pass = binding.loginpassworduser.text.toString()
            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val shift = Intent(this, UserHomepage::class.java)
                        startActivity(shift)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }

            } else {
                Toast.makeText(this, "Fill empty fields", Toast.LENGTH_SHORT).show()
            }

        }
        val usersignuppage = findViewById<Button>(R.id.usersignupbutton)
        usersignuppage.setOnClickListener {
            val shift = Intent(this, usersignup::class.java)
            startActivity(shift)
        }
    }
}