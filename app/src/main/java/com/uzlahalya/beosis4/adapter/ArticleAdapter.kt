package com.uzlahalya.beosis4.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uzlahalya.beosis4.R
import com.uzlahalya.beosis4.activity.DetailArticleActivity
import com.uzlahalya.beosis4.databinding.ItemArticleBinding
import com.uzlahalya.beosis4.model.ArticleItem

class ArticleAdapter(var context: Context) : RecyclerView.Adapter<ArticleAdapter.MyViewHolder>()  {

    private var dataArticle : List<ArticleItem> = listOf()

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemArticleBinding.bind(view)

        fun initializing(article: ArticleItem) {
            binding.apply {
                Glide.with(context).load(article.image).into(ivImageItemArticle);
                tvTitleItemArticle.text = article.articleTitle
                tvCountryNameArticle.text = article.country

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailArticleActivity::class.java)
                    intent.putExtra(DetailArticleActivity.EXTRA_ARTICLE, article)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.initializing(dataArticle[position])
    }

    override fun getItemCount(): Int {
        return dataArticle.size
    }

    fun setData(data: List<ArticleItem>){
        dataArticle = data
        notifyDataSetChanged()
    }
}