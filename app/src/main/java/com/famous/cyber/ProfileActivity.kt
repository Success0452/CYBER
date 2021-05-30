package com.famous.cyber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.famous.cyber.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {
    //view binding
    private lateinit var binding: ActivityProfileBinding
    //Actionbar
    private lateinit var actionBar: ActionBar
    //firebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.main.setOnClickListener{
            startActivity(Intent(this, Computer::class.java))
        }
        //configure actionBar
        actionBar = supportActionBar!!
        actionBar.title = "Profile"

        //init firebase
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        //handle click, logout
        binding.logoutProfile.setOnClickListener{
            firebaseAuth.signOut()
            checkUser()
        }
    }

    private fun checkUser() {
        //check user is logged in or not
        val firebaseUser =firebaseAuth.currentUser
        if (firebaseUser != null){
            //user is not null, user is logged in, get user info
            val email = firebaseUser.email
            //set to text view
            binding.emailProfile.text = email

        }else{
            //user is null, user is not logged in, switch to login activity
            startActivity(Intent(this, Login::class.java))
            finish()
        }
    }
}