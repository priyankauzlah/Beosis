package com.uzlahalya.beosis4.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import com.uzlahalya.beosis4.R
import com.uzlahalya.beosis4.databinding.ActivityDetailArticleBinding
import com.uzlahalya.beosis4.fragment.ArticleFragment
import kotlinx.android.synthetic.main.activity_detail_article.*
import kotlinx.android.synthetic.main.activity_detail_scholarship.*

class DetailArticleActivity : AppCompatActivity() {

    private lateinit var detailArticleBinding : ActivityDetailArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailArticleBinding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(detailArticleBinding.root)

        supportActionBar?.hide()


//        val callback = articleFragment().onBackPressedDispatcher.addCallback(this) {
//            // Handle the back button event
//        }

        ib_arrow_back_detailarticle.setOnClickListener {
            startActivity(
                Intent(
                    ArticleFragment.getLaunchService(
                        this
                    )
                )
            )
        }

        detailArticleBinding.tvTitleDetailarticle.text =
            getIntent().getStringExtra("ARTICLETITLE")
        detailArticleBinding.tvCountryNameDetailarticle.text =
            getIntent().getStringExtra("ARTICLECOUNTRY")
        detailArticleBinding.tvContentDetailarticle.text =
            getIntent().getStringExtra("ARTICLECONTENT")

//        back()

    }
//
//    override fun onBackPressed() {
//        if (ib_arrow_back_detailarticle.focusedChild == null) {
//            updateFocus(FocusSearchAction.BACK_PRESSED)
//            return true
//        }
//        return false
//    }


//    private fun back() {
//        detailArticleBinding.ibArrowBackDetailarticle.setOnClickListener{
//            startActivity(ArticleFragment.getLaunchService(this))
//        }
//    }
}