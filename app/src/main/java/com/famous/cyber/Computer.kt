package com.famous.cyber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.famous.cyber.databinding.ActivityComputerBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Computer : AppCompatActivity() {

    private lateinit var binding : ActivityComputerBinding

    private lateinit var database: FirebaseDatabase

    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComputerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        binding.add.setOnClickListener{
            sendData()
        }

        binding.check.setOnClickListener{
            startActivity(Intent(applicationContext, GetData::class.java))
        }
    }

    private fun sendData(){
        val userId = binding.userId.text.toString().trim()
        val computerName = binding.computerName.text.toString().trim()
        val userName = binding.UserName.text.toString().trim()

        if (userId.isNotEmpty() && computerName.isNotEmpty() && userName.isNotEmpty())
        {
            var model = DatabaseModel(userId,userName)
            var id = reference.push().key

            //Set Data To Firebase
            reference.child(id!!).setValue(model)

            binding.userId.setText("")
            binding.computerName.setText("")
            binding.UserName.setText("")
        }else
        {
            Toast.makeText(this, "All field are required", Toast.LENGTH_SHORT).show()
        }
    }
}