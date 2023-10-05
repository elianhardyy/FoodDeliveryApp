package com.example.fooddeliveryapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
    private lateinit var tvUsername:TextView
    private lateinit var btnLogout : Button
    private lateinit var reference : DatabaseReference
    private lateinit var mAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        tvUsername = findViewById(R.id.tvUsername)
        btnLogout = findViewById<Button>(R.id.btnLogout)
        val user = FirebaseAuth.getInstance().currentUser
        reference = FirebaseDatabase.getInstance().getReference("Users")
        val userId = user!!.uid
        mAuth = Firebase.auth

        btnLogout.setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

        reference.child(userId).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val userProfile = snapshot.getValue(User::class.java)
                if(userProfile != null)
                {
                    val username = userProfile.username

                    tvUsername.setText("Welcome "+ username)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@HomeActivity, "${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}