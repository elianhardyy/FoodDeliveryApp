package com.example.fooddeliveryapp2.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.fooddeliveryapp2.Fragment.CartFragment
import com.example.fooddeliveryapp2.Fragment.HomeFragment
import com.example.fooddeliveryapp2.Fragment.NotificationsFragment
import com.example.fooddeliveryapp2.Fragment.SettingsFragment
import com.example.fooddeliveryapp2.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
//    private lateinit var tvUsername:TextView
//    private lateinit var btnLogout : Button
//    private lateinit var reference : DatabaseReference
//    private lateinit var mAuth : FirebaseAuth
    private lateinit var bottomNavigation : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

//        tvUsername = findViewById(R.id.tvUsername)
//        btnLogout = findViewById<Button>(R.id.btnLogout)
        bottomNavigation = findViewById(R.id.bottom_navigation)
//        val user = FirebaseAuth.getInstance().currentUser
//        reference = FirebaseDatabase.getInstance().getReference("Users")
//        val userId = user!!.uid
//        mAuth = Firebase.auth
        replaceFragment(HomeFragment())
        bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(HomeFragment())
                R.id.cart -> replaceFragment(CartFragment())
                R.id.notification -> replaceFragment(NotificationsFragment())
                R.id.setting -> replaceFragment(SettingsFragment())
                else->{}
            }
            true
        }
        var badge = bottomNavigation.getOrCreateBadge(R.id.notification)
        badge.number = 9
        //bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
//        btnLogout.setOnClickListener {
//            mAuth.signOut()
//            startActivity(Intent(this,MainActivity::class.java))
//        }
//
//        reference.child(userId).addListenerForSingleValueEvent(object : ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val userProfile = snapshot.getValue(User::class.java)
//                if(userProfile != null)
//                {
//                    val username = userProfile.username
//
//                    tvUsername.setText("Welcome "+ username)
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(this@HomeActivity, "${error.message}", Toast.LENGTH_SHORT).show()
//            }
//        })


    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.home_container,fragment).commit()
    }
//    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
//        when(it.itemId){
//            R.id.item_1 -> {
//                val fragment = HomeFragment.newInstance()
//                addFragment(fragment)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.item_2 -> {
//                val fragment = FoodFragment.newInstance()
//                addFragment(fragment)
//                return@OnNavigationItemSelectedListener true
//            }
//            else -> false
//        }
//    }
//    private fun addFragment(fragment: Fragment) {
//        supportFragmentManager
//            .beginTransaction()
////            .setCustomAnimations(com.google.android.material.R.anim.design_bottom_sheet_slide_in,
////                com.google.android.material.R.anim.design_bottom_sheet_slide_out)
//            .replace(R.id.home_container,fragment,fragment.javaClass.simpleName)
//            .commit()
//    }

}