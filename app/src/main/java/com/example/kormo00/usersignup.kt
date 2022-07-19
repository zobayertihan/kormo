package com.example.kormo00

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.kormo00.databinding.ActivityUsersignupBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class usersignup : AppCompatActivity() {


    private lateinit var binding: ActivityUsersignupBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()


        binding.usersignup.setOnClickListener {
            val name = binding.username.text.toString()
            val nid = binding.usernid.text.toString()
            val mobile = binding.usermobile.text.toString()
            val email = binding.useremail.text.toString()
            val pass = binding.userpassword.text.toString()
            val confpass = binding.userconfirmpassword.text.toString()
            val add = binding.useraddress.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Database")
            val Database = Users(name, nid, mobile, email, pass, confpass, add)
            database.child(name).setValue(Database).addOnSuccessListener {
                binding.username.text.clear()
                binding.usernid.text.clear()
                binding.usermobile.text.clear()
                binding.useremail.text.clear()
                binding.userpassword.text.clear()
                binding.userconfirmpassword.text.clear()
                binding.useraddress.text.clear()
            }

            if (name.isNotEmpty() && nid.isNotEmpty() && mobile.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && confpass.isNotEmpty() && add.isNotEmpty()) {
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

        }

        val userloginpage = findViewById<Button>(R.id.userloginbutton)
        userloginpage.setOnClickListener {
            val shift = Intent(this, UserLogin::class.java)
            startActivity(shift)
        }
    }
}