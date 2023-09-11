package com.example.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.firebaseauth.databinding.ActivityLoginBinding
import com.example.firebaseauth.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_register)
        auth = Firebase.auth
        binding.btnRegister.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            binding.progressBar.visibility = View.VISIBLE

            if(TextUtils.isEmpty(email)){
                Toast.makeText(this,"Enter email",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(password)){
                Toast.makeText(this,"Enter password", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    binding.progressBar.visibility = View.GONE
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(
                            this@Register, "Account Created.", Toast.LENGTH_SHORT,
                        ).show()
                    }
                    else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                             this@Register, "Authentication failed.", Toast.LENGTH_SHORT,
                        ).show()
                    }
                }

        }

        binding.loginNow.setOnClickListener {
            val i = Intent(applicationContext,ActivityLoginBinding::class.java)
            startActivity(i)
            finish()
        }
    }
}