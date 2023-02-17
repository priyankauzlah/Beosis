package com.uzlahalya.beosis4.data

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uzlahalya.beosis4.R

class ArticleAdapter(var data : ArrayList<Article>, var context: Activity?, var clickListener: ArticleAdapter.onArticleItemClickListener) : RecyclerView.Adapter<ArticleAdapter.MyViewHolder>()  {

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val articleImage = view.findViewById<ImageView>(R.id.iv_image_item_article)
        val articleTitle = view.findViewById<TextView>(R.id.tv_title_item_article)
        val articleCountry = view.findViewById<TextView>(R.id.tv_country_name_article)

        fun initialize(item: Article, action: ArticleAdapter.onArticleItemClickListener) {
            articleImage.setImageResource(item.image)
            articleTitle.text = item.title
            articleCountry.text = item.country

            itemView.setOnClickListener {
                action.onItemClick(item, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleAdapter.MyViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleAdapter.MyViewHolder, position: Int) {
        holder.initialize(data.get(position),clickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface onArticleItemClickListener{
        fun onItemClick(item: Article, position: Int)
    }
}