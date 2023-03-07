package com.uzlahalya.beosis4.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uzlahalya.beosis4.R
import com.uzlahalya.beosis4.activity.DetailArticleActivity
import com.uzlahalya.beosis4.data.Article
import com.uzlahalya.beosis4.databinding.ItemArticleBinding

class ArticleAdapter(var context: Context) : RecyclerView.Adapter<ArticleAdapter.MyViewHolder>(), Filterable {

    private var dataArticle : List<Article> = listOf()

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemArticleBinding.bind(view)

        fun initializing(article: Article) {
            binding.apply {
                Glide.with(context).load(article.image).into(ivImageItemArticle)
                tvTitleItemArticle.text = article.title
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

    fun setData(data: List<Article>){
        dataArticle = data
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredArticles = dataArticle.searchable(constraint.toString())
                return FilterResults().apply {
                    values = filteredArticles
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataArticle = results?.values as ArrayList<Article>
                notifyDataSetChanged()
            }

        }
    }
}

// Logic for article filtering
fun List<Article>.searchable(query: String): List<Article> {
    val searchQuery = query.lowercase()
    val originalList = this
    return this.filter {
        it.country.lowercase().contains(searchQuery) ||
                it.title.lowercase().contains(searchQuery)
    }.ifEmpty { originalList }
}