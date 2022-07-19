package com.example.kormo00

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import com.example.kormo00.databinding.ActivityUserLoginBinding
import com.example.kormo00.databinding.ActivityUsersignupBinding
import com.example.kormo00.databinding.ActivityWorkerLoginBinding
import com.google.firebase.auth.FirebaseAuth


class WorkerLogin : AppCompatActivity() {
    private lateinit var binding: ActivityWorkerLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityWorkerLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.workerlogin.setOnClickListener {
            val email = binding.loginusernameworker.text.toString()
            val pass = binding.loginpasswordworker.text.toString()
            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val shift = Intent(this, WorkerHomepage::class.java)
                        startActivity(shift)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }

            } else {
                Toast.makeText(this, "Fill empty fields", Toast.LENGTH_SHORT).show()
            }

        }

        val workersignuppage = findViewById<Button>(R.id.workersignupbutton)
        workersignuppage.setOnClickListener {
            val shift = Intent(this,workersignup::class.java)
            startActivity(shift)
        }
    }
}