package com.example.mastproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        val edtAddress = findViewById<TextView>(R.id.edtAddress)
        val edtPassword = findViewById<TextView>(R.id.edtPassword)
        val edtConfirm = findViewById<TextView>(R.id.edtConfirm)
        val btnAdmin = findViewById<Button>(R.id.btnAdmin)

        btnAdmin.setOnClickListener {
            val email = edtAddress.text.toString()
            val password = edtPassword.text.toString()
            val confirmPassword = edtConfirm.text.toString()

            val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
            var isValid = true


            if (!emailRegex.matches(email)) {
                Toast.makeText(this@signup, "Invalid email format", Toast.LENGTH_SHORT).show()
                isValid = false
            }

            if (password.length < 6) {
                Toast.makeText(this@signup, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                isValid = false
            }

            if (password != confirmPassword) {
                Toast.makeText(this@signup, "Passwords do not match.", Toast.LENGTH_SHORT).show()
                isValid = false
            }

            if (isValid) {
                Toast.makeText(this@signup, "Welcome to the app", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,menu::class.java)
                startActivity(intent)
            }
        }


    }
}