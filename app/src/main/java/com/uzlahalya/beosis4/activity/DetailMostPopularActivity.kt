package com.uzlahalya.beosis4.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.uzlahalya.beosis4.R
import com.uzlahalya.beosis4.data.Scholarship
import com.uzlahalya.beosis4.databinding.ActivityDetailMostPopularBinding
import com.uzlahalya.beosis4.databinding.ActivityDetailScholarshipBinding
import kotlinx.android.synthetic.main.activity_detail_most_popular.*
import kotlinx.android.synthetic.main.activity_detail_scholarship.*

class DetailMostPopularActivity : AppCompatActivity() {

    private lateinit var detailMostPopularBinding: ActivityDetailMostPopularBinding

    companion object{
        const val EXTRA_MOST_POPULAR = "extra_most_popular"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailMostPopularBinding = ActivityDetailMostPopularBinding.inflate(layoutInflater)
        setContentView(detailMostPopularBinding.root)

        supportActionBar?.hide()

        ib_arrow_back_detail_popular_scholarship.setOnClickListener {
            finish()
        }

        val dataMostPopular = intent.getParcelableExtra<Scholarship>(EXTRA_MOST_POPULAR)

        detailMostPopularBinding.apply {
            dataMostPopular?.let {
                Glide.with(this@DetailMostPopularActivity).load(dataMostPopular.logo).into(ivImageDetailPopularScholarship)
            }

            tvTitleScholarshipDetailPopularScholarship.text = dataMostPopular?.name
            tvUniDetailPopularScholarship.text = dataMostPopular?.university
            tvCountryDetailPopularScholarship.text = dataMostPopular?.country
            tvMajorDetailPopularScholarship.text = dataMostPopular?.majors
            tvDegreeDetailPopularScholarship.text = dataMostPopular?.degree
            tvDateOpenregistrationDetailPopularScholarship.text = dataMostPopular?.openregistration
            tvDateDeadlineDetailPopularScholarship.text = dataMostPopular?.closeregistration
            tvBenefitDetailPopularScholarship.text = dataMostPopular?.benefit
            tvRequirementsDetailPopularScholarship.text = dataMostPopular?.requirement
            btnMoreInformationMostPopular.setOnClickListener {
                moreInformation(dataMostPopular?.link.toString())
            }
        }
    }

    fun moreInformation(mostInformationLink : String){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(mostInformationLink)
        startActivity(intent)
    }
}