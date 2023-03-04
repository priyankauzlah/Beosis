package com.uzlahalya.beosis4.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.gms.common.util.CollectionUtils.listOf
import com.uzlahalya.beosis4.R
import com.uzlahalya.beosis4.activity.DetailScholarshipActivity
import com.uzlahalya.beosis4.data.Scholarship
import com.uzlahalya.beosis4.databinding.ItemScholarshipBinding
import com.uzlahalya.beosis4.fragment.HomeFragment

class ScholarshipAdapter(
    var context: Context,
) : RecyclerView.Adapter<ScholarshipAdapter.MyViewHolder>() {

    private var dataScholarship : List<Scholarship> = listOf()

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemScholarshipBinding.bind(view)


        fun initialize(ship: Scholarship) {
            binding.apply {
                Glide.with(context).load(ship.logo).into(ivImageItemScholar);
                tvTitleItemScholar.text = ship.name
                tvUniItemScholar.text = ship.university
                tvCountryItemScholar.text = ship.country
                tvLevelItemScholar.text = ship.degree
                tvDateDeadlineDetailscholarship.text = ship.closeregistration
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
        holder.initialize(dataScholarship[position])
    }

    override fun getItemCount(): Int {
        return dataScholarship.size
    }

    fun setData(data: List<Scholarship>){
        dataScholarship = data
        notifyDataSetChanged()
    }
}