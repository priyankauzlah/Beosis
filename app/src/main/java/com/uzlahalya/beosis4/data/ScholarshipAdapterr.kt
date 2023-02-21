package com.uzlahalya.beosis4.data

import android.app.Activity
import android.content.ClipData
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uzlahalya.beosis4.R
import com.uzlahalya.beosis4.activity.DetailScholarshipActivity
import com.uzlahalya.beosis4.databinding.ItemScholarshipBinding
import com.uzlahalya.beosis4.model.ScholarshipItem

class ScholarshipAdapterr(
    var data: ArrayList<Scholarship>,
    var context: Activity?,
    var clickListener: onScholarshipItemClickListener
) : RecyclerView.Adapter<ScholarshipAdapterr.MyViewHolder>() {

    private var dataScholarship : List<ScholarshipItem> = listOf()

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemScholarshipBinding.bind(view)

        fun initialize(ship: ScholarshipItem) {
            binding.apply {
                tvTitleItemScholar.text = ship.scholarshipName
                tvUniItemScholar.text = ship.universityName
                tvCountryItemScholar.text = ship.country
                tvLevelItemScholar.text = ship.degree
                tvDateDeadlineDetailscholarship.text = ship.closeRegister

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailScholarshipActivity::class.java)
                    intent.putExtra(DetailScholarshipActivity.EXTRA_SCHOLARSHIP, ship)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_scholarship, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.initialize(data.get(position), clickListener)
        holder.initialize(dataScholarship[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface onScholarshipItemClickListener {
        fun onItemClick(item: Scholarship, position: Int)
    }
}