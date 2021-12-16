package com.example.a19012011117_meetpatel_assignment_2.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a19012011117_meetpatel_assignment_2.R
import com.example.a19012011117_meetpatel_assignment_2.Collage
import com.example.a19012011117_meetpatel_assignment_2.adapter.ListAdapter
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage

class HomeFragment : AppCompatActivity() {

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mCollages:MutableList<Collage>
    private lateinit var listAdapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)

        var mRecyclerView = findViewById<RecyclerView>(R.id.mRecyclerView)
        var myDataLoaderProgressBar = findViewById<ProgressBar>(R.id.myDataLoaderProgressBar)


        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@HomeFragment)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mCollages = ArrayList()
        listAdapter = ListAdapter(this@HomeFragment,mCollages)
        mRecyclerView.adapter = listAdapter
        /**set Firebase Database*/
        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("teachers_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@HomeFragment,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                mCollages.clear()
                for (teacherSnapshot in snapshot.children){
                    val upload = teacherSnapshot.getValue(Collage::class.java)
                    upload!!.key = teacherSnapshot.key
                    mCollages.add(upload)

                }
                listAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE

            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}