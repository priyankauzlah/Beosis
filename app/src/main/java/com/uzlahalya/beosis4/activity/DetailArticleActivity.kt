package com.uzlahalya.beosis4.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uzlahalya.beosis4.data.Article
import com.uzlahalya.beosis4.databinding.ActivityDetailArticleBinding
import kotlinx.android.synthetic.main.activity_detail_article.*

class DetailArticleActivity : AppCompatActivity() {

    private lateinit var detailArticleBinding : ActivityDetailArticleBinding

    companion object{
        const val EXTRA_ARTICLE = "extra_article"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailArticleBinding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(detailArticleBinding.root)

        supportActionBar?.hide()

        ib_arrow_back_detailarticle.setOnClickListener {
            finish()
        }

        val dataArticle = intent.getParcelableExtra<Article>(DetailArticleActivity.EXTRA_ARTICLE)

        detailArticleBinding.apply {

            tvTitleDetailarticle.text = dataArticle?.title
            tvCountryNameDetailarticle.text = dataArticle?.country
            tvContentDetailarticle.text = dataArticle?.content
            tv_source_link.text = dataArticle?.url
        }
    }
}