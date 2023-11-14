package com.example.fooddeliveryapp2.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fooddeliveryapp2.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var mauth : FirebaseAuth
    private lateinit var etEmail : TextInputEditText
    private lateinit var etPassword : TextInputEditText
    private lateinit var btnSubmit : ConstraintLayout
    private lateinit var emailLayout : TextInputLayout
    private lateinit var passwordLayout : TextInputLayout
    private lateinit var tvForgotPassword : TextView
    private lateinit var tvRegister : TextView
    private lateinit var tvWarning : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etEmail = findViewById(R.id.inputemailLogin)
        etPassword = findViewById(R.id.inputpasswordLogin)
        btnSubmit = findViewById(R.id.btnLoginConstraint)
        tvRegister = findViewById(R.id.tvRegister)
        tvWarning = findViewById(R.id.tvWarning)
        tvForgotPassword = findViewById(R.id.tvForgotPassword)
        tvRegister.setOnClickListener {
            startActivity(Intent(this, AuthActivity::class.java))
        }
        tvForgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
        btnSubmit.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            if(validation(email,password)){
                login(email,password)
            }
        }
    }
    private fun login(email:String, password:String)
    {
        mauth = Firebase.auth
        mauth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    finish()
                    Toast.makeText(this,"Authentication succesfull",Toast.LENGTH_LONG).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }else{
                    tvWarning.visibility = View.VISIBLE
                    Toast.makeText(this,"Authentication Failed",Toast.LENGTH_SHORT).show()
                }
            }
    }
    //kalau bisa validation pakai rest parameter
    private fun validation(email:String, password: String):Boolean
    {
        emailLayout = findViewById(R.id.emailLayoutLogin)
        passwordLayout = findViewById(R.id.passwordLayoutLogin)

        if(email.isEmpty())
        {
            emailLayout.error = "This field is required"
            return false
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailLayout.error = "Field must email format"
            return false
        }
        if(password.isEmpty())
        {
            passwordLayout.error = "This field is required"
            return false
        }
        if(password.length <= 7)
        {
            passwordLayout.error = "Password must be more than 8 characters"
            return false
        }
        return true
    }

}