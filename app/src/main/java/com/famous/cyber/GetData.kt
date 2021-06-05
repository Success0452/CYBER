package com.famous.cyber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.famous.cyber.databinding.ActivityComputerBinding
import com.famous.cyber.databinding.ActivityGetDataBinding
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_get_data.*

class GetData : AppCompatActivity() {

    private lateinit var binding : ActivityGetDataBinding

    private lateinit var database: FirebaseDatabase

    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        getData()
    }

    private fun getData()
    {
        reference.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
               Log.e("cancel", p0.toString())

            }

            override fun onDataChange(p0: DataSnapshot) {
                var list = ArrayList<DatabaseModel>()
                for (data in p0.children)
                {
                    var model = data.getValue(DatabaseModel::class.java)
                    list.add(model as DatabaseModel)
                }
                if (list.size > 0 )
                {

                    var adapter = DataAdapter(list)
                    recyclerview.adapter = adapter
                }
            }

        })
    }
}