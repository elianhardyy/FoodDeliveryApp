package com.example.fooddeliveryapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.play.core.integrity.IntegrityTokenRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class AuthActivity : AppCompatActivity() {
    private lateinit var mauth: FirebaseAuth
    private lateinit var etUsername : TextInputEditText
    private lateinit var etAddress : TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var etPasswordConfirm : TextInputEditText
    private lateinit var btnSubmit : Button
    private lateinit var usernameLayout: TextInputLayout
    private lateinit var addressLayout: TextInputLayout
    private lateinit var emailLayout : TextInputLayout
    private lateinit var passwordLayout : TextInputLayout
    private lateinit var passwordConfirmLayout: TextInputLayout
    private lateinit var dbRef:DatabaseReference
    private lateinit var tvLogin : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        etUsername = findViewById(R.id.inputusername)
        etAddress = findViewById(R.id.inputaddress)
        etEmail = findViewById<TextInputEditText>(R.id.inputemail)
        etPassword = findViewById(R.id.inputpassword)
        btnSubmit = findViewById(R.id.btnSubmitRegist)
        tvLogin = findViewById(R.id.tvLogin)
        tvLogin.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        dbRef = FirebaseDatabase.getInstance().getReference("Users")
        btnSubmit.setOnClickListener {
            val email = etEmail.text.toString() //kalau error berarti variable di dalam setonclick / object/ funciton
            val password = etPassword.text.toString()
            val username = etUsername.text.toString()
            val address = etAddress.text.toString()
            if(validation(email,password,username,address)){
                register(email,password,username,address)
            }

        }
    }
    private fun register(email:String, password:String,username:String,address:String)
    {
        mauth = Firebase.auth

        mauth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) {

                if(it.isSuccessful){
                    val users = User(username, address, email)
                    dbRef
                        .child(FirebaseAuth.getInstance().currentUser!!.uid)
                        .setValue(users).addOnCompleteListener{
                            Toast.makeText(this,"Succesfull",Toast.LENGTH_LONG).show()
                        }
                    etUsername.text?.clear()
                    etAddress.text?.clear()
                    etEmail.text?.clear()
                    etPassword.text?.clear()
                    etPasswordConfirm.text?.clear()
                    startActivity(Intent(this,LoginActivity::class.java))
                    finish()
                }else{
                    Log.e("error",it.exception.toString())
                }
            }

    }
//
    private fun validation(email:String, password: String,username: String,address:String) : Boolean
    {
        etPasswordConfirm = findViewById(R.id.inputpasswordconfirm)
        passwordConfirmLayout = findViewById(R.id.passwordconfirmLayout)
        val passwordConfirm = etPasswordConfirm.text.toString()
        usernameLayout = findViewById(R.id.userNameLayout)
        addressLayout = findViewById(R.id.addressLayout)
        emailLayout = findViewById(R.id.emailLayout)
        passwordLayout = findViewById(R.id.passwordLayout)
        if(username.isEmpty()){
            usernameLayout.error = "This is required"
            return false
        }
        if(address.isEmpty()){
            addressLayout.error = "This is required"
            return false
        }
        if(email.isEmpty()){
            emailLayout.error = "This is required"
            return false
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailLayout.error = "Check email format"
            return false
        }
        if(password.isEmpty()){
            passwordLayout.error = "This is required"
            return false
        }
        if(password.length <= 7){
            passwordLayout.error = "Password must be more than 8 characters"
            return false
        }
        if(passwordConfirm.isEmpty()){
            passwordConfirmLayout.error = "This is required"
            return false
        }
        if(passwordConfirm != password){
            passwordLayout.error = "Password confirm not matches"
            return false
        }
        return true
    }

}