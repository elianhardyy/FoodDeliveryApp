package com.example.fooddeliveryapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var btnAuth:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAuth = findViewById(R.id.btnAuth)

        btnAuth.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        Handler(Looper.getMainLooper()).postDelayed({
            mAuth = Firebase.auth
            val user = mAuth.currentUser
            if (user != null){
                startActivity(Intent(this,HomeActivity::class.java))
                finish()
            }else{
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
        },90000)


    }

}