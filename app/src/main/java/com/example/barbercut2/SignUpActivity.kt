package com.example.barbercut2

import android.content.Intent
import android.widget.EditText
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var emailEdit: EditText
    private lateinit var passwordEdit: EditText
    private lateinit var signUpButton: Button
    private lateinit var nameEdit: EditText
    private lateinit var changeButton2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        emailEdit = findViewById(R.id.emailEdit)
        passwordEdit = findViewById(R.id.passwordEdit)
        signUpButton = findViewById(R.id.signUpButton)
        nameEdit = findViewById(R.id.nameEdit)
        changeButton2 = findViewById(R.id.changeButton2)

        change()

        setup()
    }

    private fun change() {
        title = "Cambiar a registro"
        changeButton2.setOnClickListener {
            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setup() {

        title = "Autenticacion"


        signUpButton.setOnClickListener {
            if (nameEdit.text.isNotEmpty() && emailEdit.text.isNotEmpty() && passwordEdit.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEdit.text.toString(),passwordEdit.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }

            }
        }
    }
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se a producido un error de autenticacion")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

    private fun showHome(email: String, provider: ProviderType){
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("Email",email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }
}
