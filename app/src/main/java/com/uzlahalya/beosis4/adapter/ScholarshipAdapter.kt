package com.uzlahalya.beosis4.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uzlahalya.beosis4.R
import com.uzlahalya.beosis4.activity.DetailArticleActivity
import com.uzlahalya.beosis4.model.ScholarshipItem
import kotlinx.android.synthetic.main.item_scholarship.view.*

class ScholarshipAdapter (var context: Context) : RecyclerView.Adapter<ScholarshipAdapter.ViewHolder>() {

    private var scholarship : List<ScholarshipItem> = ArrayList()

    fun setData(items : List<ScholarshipItem>){
        scholarship = items
        notifyDataSetChanged()
    }

    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
        fun bind (data: ScholarshipItem){
            with(itemView){
                tv_title_item_scholar.text = data.scholarshipName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_scholarship, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(scholarship.get(position))
    }

    override fun getItemCount(): Int = scholarship.size

}