package com.example.fooddeliveryapp2.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import com.example.fooddeliveryapp2.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var etEmail : TextInputEditText
    private lateinit var emailLayout : TextInputLayout
    private lateinit var mAuth : FirebaseAuth
    private lateinit var btnSubmit : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        etEmail = findViewById(R.id.inputemailForgotPass)
        btnSubmit = findViewById(R.id.btnSubmitForgotPass)

        btnSubmit.setOnClickListener {
            val email = etEmail.text.toString()
            if(validation(email)){
                forgotPassword(email)
            }
        }
    }
    private fun forgotPassword(email:String)
    {
        mAuth = Firebase.auth
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener{
            if(it.isSuccessful){
                Toast.makeText(this,"Sent email Success",Toast.LENGTH_LONG).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }else{
                Toast.makeText(this,"failed",Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun validation(email:String) : Boolean
    {
        emailLayout = findViewById(R.id.emailLayoutForgotPass)

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
        return true
    }
}