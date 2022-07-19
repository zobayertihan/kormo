package com.example.kormo00

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.Button
import android.widget.Toast
import com.example.kormo00.databinding.ActivityUsersignupBinding
import com.example.kormo00.databinding.ActivityWorkersignupBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class workersignup : AppCompatActivity() {
    private lateinit var binding: ActivityWorkersignupBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var radioGroup: RadioGroup
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkersignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        val workerloginpage = findViewById<Button>(R.id.workerloginbutton)
        workerloginpage.setOnClickListener {
            val shift = Intent(this,WorkerLogin::class.java)
            startActivity(shift)
        }

        binding.workersignup.setOnClickListener {
            val name = binding.workername.text.toString()
            val nid = binding.workernid.text.toString()
            val mobile = binding.workermobile.text.toString()
            val email = binding.workeremail.text.toString()
            val pass = binding.workerpassword.text.toString()
            val confpass = binding.workerconfirmpassword.text.toString()
            val exp = binding.workerexperience.text.toString()
            val occu = binding.workeroccupation.text.toString()
            if (name.isNotEmpty() && nid.isNotEmpty() && mobile.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && confpass.isNotEmpty() && exp.isNotEmpty() && occu.isNotEmpty()) {
                if (pass == confpass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(
                                this,
                                "Sign Up Successful. Go to login page",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Fill empty fields", Toast.LENGTH_SHORT).show()
            }

            database = FirebaseDatabase.getInstance().getReference("Database")
            val Database = Worker(name, nid, mobile, email, pass, confpass, exp, occu)
            /*database.child(name).setValue(Database).addOnSuccessListener {
                binding.workername.text.clear()
                binding.workernid.text.clear()
                binding.workermobile.text.clear()
                binding.workeremail.text.clear()
                binding.workerpassword.text.clear()
                binding.workerconfirmpassword.text.clear()
                binding.workerexperience.text.clear()
                binding.workeroccupation.text.clear()
            }.addOnFailureListener { Toast.makeText(this, "Failled", Toast.LENGTH_SHORT).show() }*/

        }
        firebaseAuth.signOut()
    }
}