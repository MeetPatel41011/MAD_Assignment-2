package com.example.a19012011117_meetpatel_assignment_2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.a19012011117_meetpatel_assignment_2.R
import com.example.a19012011117_meetpatel_assignment_2.imageload.loadImage

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val intss = intent
        var nameT = intss.getStringExtra("NAMET")
        var desT = intss.getStringExtra("DESCRIT")
        var imgT = intss.getStringExtra("IMGURI")

        var nameDetailTextView = findViewById<TextView>(R.id.nameDetailTextView)
        var descriptionDetailTextView = findViewById<TextView>(R.id.descriptionDetailTextView)
        var teacherDetailImageView = findViewById<ImageView>(R.id.teacherDetailImageView)

        nameDetailTextView.text = nameT
        descriptionDetailTextView.text = desT
        teacherDetailImageView.loadImage(imgT)


    }
}