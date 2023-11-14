package com.example.fooddeliveryapp2.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.fooddeliveryapp2.Activity.LoginActivity
import com.example.fooddeliveryapp2.Activity.MainActivity
import com.example.fooddeliveryapp2.Adapter.CategoryAdapter
import com.example.fooddeliveryapp2.Data.Category
import com.example.fooddeliveryapp2.R
import com.example.fooddeliveryapp2.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var mAuth : FirebaseAuth
    private lateinit var btnSignOut : Button
    private lateinit var reference: DatabaseReference
    lateinit var tvUsernameHome : TextView
    lateinit var categoriesRecyclerView: RecyclerView
    lateinit var popularRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        var v:View = inflater.inflate(R.layout.fragment_home, container, false)
        btnSignOut = v.findViewById(R.id.btnSignOutFragment)
        categoriesRecyclerView = v.findViewById(R.id.categoriesRecyclerView)
        popularRecyclerView = v.findViewById(R.id.popularRecyclerView)
        reference = FirebaseDatabase.getInstance().getReference("Users")
        tvUsernameHome = v.findViewById(R.id.textViewUsername)
        val user = FirebaseAuth.getInstance().currentUser
        val userId = user!!.uid
        mAuth = Firebase.auth
        btnSignOut.setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(activity,MainActivity::class.java))
        }
        reference.child(userId).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val userProfile = snapshot.getValue(User::class.java)
                if(userProfile != null){
                    val username = userProfile.username

                    tvUsernameHome.setText("Hi, "+username)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity,"${error.message}",Toast.LENGTH_LONG).show()
            }
        })
        recyclerViewPopular()
        recyclerViewCategory()


        return v
    }

    private fun recyclerViewPopular() {

    }

    private fun recyclerViewCategory() {
        var linearLayoutmanager : LinearLayoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        categoriesRecyclerView.layoutManager = linearLayoutmanager
        var categoryList : ArrayList<Category> = arrayListOf<Category>()
        categoryList.add(Category("pizza","cat_1"))
        categoryList.add(Category("burger","cat_2"))
        categoryList.add(Category("hotdog","cat_3"))
        categoryList.add(Category("drink","cat_4"))
        categoryList.add(Category("donut","cat_5"))

        var adapter = CategoryAdapter(categoryList)
        categoriesRecyclerView.adapter = adapter
    }

    companion object {

        fun newInstance() : HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment

        }
    }
}