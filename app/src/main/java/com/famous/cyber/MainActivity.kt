package com.famous.cyber

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.famous.cyber.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    //view binding
    private lateinit var binding: ActivityMainBinding

    //progressDialog
    private lateinit var progressDialog: ProgressDialog

    //progressBar
    private lateinit var actionBar: ActionBar

    private lateinit var nav: NavigationView

    //drawer layout
    private lateinit var mDrawerLayout : DrawerLayout

    //firebaseAuth
    private lateinit var firebaseAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(toolbar)
        //drawer
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        //configure progressBar
        actionBar = supportActionBar!!
        actionBar.title = "DASHBOARD"

        //init firebase
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()


    }
    private fun checkUser() {
        //check user is logged in or not
        val firebaseUser =firebaseAuth.currentUser
        if (firebaseUser != null){
            //user is not null, user is logged in, get user info
            val email = firebaseUser.email

            //set to text view
//            binding.email4.text = email

        }else{
            //user is null, user is not logged in, switch to login activity
            startActivity(Intent(this, Login::class.java))
            finish()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.dashboard_menu -> {
                startActivity(Intent(this, DashBoard::class.java))
            }
            R.id.computer_menu -> {
                startActivity(Intent(this, DashBoard::class.java))
            }
            R.id.profile_menu -> {
                startActivity(Intent(this, ProfileActivity::class.java))
            }
            R.id.logout_menu -> {

                firebaseAuth.signOut()
                checkUser()
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else {
            super.onBackPressed()
        }
    }
}