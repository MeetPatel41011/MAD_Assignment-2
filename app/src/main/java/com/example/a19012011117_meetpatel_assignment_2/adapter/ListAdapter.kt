package com.example.a19012011117_meetpatel_assignment_2.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a19012011117_meetpatel_assignment_2.R
import com.example.a19012011117_meetpatel_assignment_2.Collage
import com.example.a19012011117_meetpatel_assignment_2.imageload.loadImage
import com.example.a19012011117_meetpatel_assignment_2.ui.DetailsActivity

class ListAdapter (var mContext:Context,var collageList:List<Collage>):
RecyclerView.Adapter<ListAdapter.ListViewHolder>()
{
    inner class ListViewHolder(var v:View): RecyclerView.ViewHolder(v){
        var imgT = v.findViewById<ImageView>(R.id.teacherImageView)
        var nameT = v.findViewById<TextView>(R.id.nameTextView)
        var descriT = v.findViewById<TextView>(R.id.descriptionTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
       var infalter = LayoutInflater.from(parent.context)
        var v = infalter.inflate(R.layout.row_item,parent,false)
        return ListViewHolder(v)
    }

    override fun getItemCount(): Int =collageList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
       var newList = collageList[position]
        holder.nameT.text = newList.name
        holder.descriT.text = newList.description
        holder.imgT.loadImage(newList.imageUrl)
        holder.v.setOnClickListener {

            val name = newList.name
            val descrip = newList.description
            val imgUri = newList.imageUrl

            val mIntent = Intent(mContext,DetailsActivity::class.java)
            mIntent.putExtra("NAMET",name)
            mIntent.putExtra("DESCRIT",descrip)
            mIntent.putExtra("IMGURI",imgUri)
            mContext.startActivity(mIntent)
        }
    }
}