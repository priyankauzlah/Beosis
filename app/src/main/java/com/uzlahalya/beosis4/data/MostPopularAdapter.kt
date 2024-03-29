package com.uzlahalya.beosis4.data

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uzlahalya.beosis4.R

class MostPopularAdapter(
    var data: ArrayList<Scholarship>,
    var context: Activity?,
    var clickListener: onMostPopularItemClickListener
) : RecyclerView.Adapter<MostPopularAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val scholarshipLogo = view.findViewById<ImageView>(R.id.iv_image_mostpopular)
        val scholarshipName = view.findViewById<TextView>(R.id.tv_title_mostpopular)
        val scholarshipUniversity = view.findViewById<TextView>(R.id.tv_uni_mostpopular)
        val scholarshipCountry = view.findViewById<TextView>(R.id.tv_country_mostpopular)
        val scholarshipDegree = view.findViewById<TextView>(R.id.tv_level_mostpopular)
        val scholarshipCloseregistration =
            view.findViewById<TextView>(R.id.tv_date_deadline_detailscholarship)


        fun initialize(item: Scholarship, action: onMostPopularItemClickListener) {
            scholarshipLogo.setImageResource(item.logo)
            scholarshipName.text = item.name
            scholarshipUniversity.text = item.university
            scholarshipCountry.text = item.country
            scholarshipDegree.text = item.degree
            scholarshipCloseregistration.text = item.closeregistration

            itemView.setOnClickListener {
                action.onItemClick(item, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_most_popular, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.initialize(data.get(position), clickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface onMostPopularItemClickListener {
        fun onItemClick(item: Scholarship, position: Int)
    }
}