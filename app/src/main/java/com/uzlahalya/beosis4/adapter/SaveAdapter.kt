package com.uzlahalya.beosis4.adapter

import androidx.recyclerview.widget.RecyclerView
import com.uzlahalya.beosis4.mvvm.database.ScholarshipEntity

class SaveAdapter():RecyclerView.Adapter <SaveAdapter.MyViewHolder>(){

    inner class MyViewHolder (private val binding: SaveItemRowBinding) :
            RecyclerView.ViewHolder(binding.root){
                fun bind(item: ScholarshipEntity){
                    binding.apply{

                    }
                }
            }
}