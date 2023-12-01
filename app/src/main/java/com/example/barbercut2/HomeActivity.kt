package com.example.barbercut2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.EditText
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth


enum class ProviderType{
    BASIC
}

class HomeActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var logoutButton: Button
    private lateinit var userText: TextView
    private lateinit var profileButton: Button
    private lateinit var agendarButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        auth = FirebaseAuth.getInstance()
        logoutButton = findViewById(R.id.logoutButton)
        profileButton = findViewById(R.id.profileButton)
        agendarButton = findViewById(R.id.agendarButton)

        val email = intent.getStringExtra("email")

        findViewById<TextView>(R.id.userText).text = email

        agenda()
        perfil()
        salir()
    }
    private fun agenda() {
        title = "Cambiar a perfil"
        agendarButton.setOnClickListener {
            val intent = Intent(this@HomeActivity, ScheduleActivity::class.java)
            startActivity(intent)
        }
    }
    private fun perfil() {
        title = "Cambiar a perfil"
        profileButton.setOnClickListener {
            val intent = Intent(this@HomeActivity, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
    private fun salir() {
        title = "Cambiar a registro"
        logoutButton.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}