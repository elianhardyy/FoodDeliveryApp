package com.example.fooddeliveryapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UpdatePasswordActivity : AppCompatActivity() {
    private lateinit var etPassword : TextInputEditText
    private lateinit var passwordLayout : TextInputLayout
    private lateinit var mAuth : FirebaseAuth
    private lateinit var btnSubmit : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_password)
        etPassword = findViewById(R.id.inputpasswordUpdatePass)
        btnSubmit = findViewById(R.id.btnSubmitUpdatePass)
        btnSubmit.setOnClickListener {
            val password = etPassword.text.toString()
            if(validation(password)){
                updatePassword(password)
            }
        }
    }
    private fun updatePassword(password:String)
    {
        val user = Firebase.auth.currentUser
        user!!.updatePassword(password).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this,"Update password success",Toast.LENGTH_LONG).show()
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }else{
                Toast.makeText(this,"Update password is failed",Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun validation(password:String) : Boolean
    {
        passwordLayout = findViewById(R.id.passwordLayoutUpdatePass)
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