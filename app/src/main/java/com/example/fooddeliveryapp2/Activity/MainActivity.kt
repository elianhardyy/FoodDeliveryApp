package com.example.fooddeliveryapp2.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fooddeliveryapp2.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var start : ConstraintLayout = findViewById(R.id.btnStart)

        start.setOnClickListener {
            mAuth = Firebase.auth
            val user = mAuth.currentUser
            if(user != null){
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }

        }
        Handler(Looper.getMainLooper()).postDelayed({
            mAuth = Firebase.auth
            val user = mAuth.currentUser
            if (user != null){
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }else{
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        },86400)


    }

}