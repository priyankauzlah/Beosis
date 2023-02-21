package com.uzlahalya.beosis4.activity

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.uzlahalya.beosis4.databinding.ActivityDetailScholarshipBinding
import com.uzlahalya.beosis4.fragment.ArticleFragment
import com.uzlahalya.beosis4.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_detail_scholarship.*

class DetailScholarshipActivity : AppCompatActivity() {

    private lateinit var detailScholarshipBinding: ActivityDetailScholarshipBinding

    val longTextWithLinks =
        "for more information click google.com/" +
                "open android.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailScholarshipBinding = ActivityDetailScholarshipBinding.inflate(layoutInflater)
        setContentView(detailScholarshipBinding.root)

        supportActionBar?.hide()
//        ib_arrow_back_detailscholarship.setOnClickListener {
//            startActivity(
//                Intent(
////                    ArticleFragment.getLauchService(
//                        this
//                    )
//                )
//            )
//        }

        getIntent().getStringExtra("SCHOLARSHIPLOGO")
            ?.let { detailScholarshipBinding.ivImageDetailscholarship.setImageResource(it.toInt()) }
        detailScholarshipBinding.tvTitleScholarshipDetailscholarship.text =
            getIntent().getStringExtra("SCHOLARSHIPNAME")
        detailScholarshipBinding.tvUniDetailscholarship.text =
            getIntent().getStringExtra("SCHOLARSHIPUNIVERSITY")
        detailScholarshipBinding.tvCountryDetailscholarship.text =
            getIntent().getStringExtra("SCHOLARSHIPCOUNTRY")
        detailScholarshipBinding.tvMajorDetailscholarship.text =
            getIntent().getStringExtra("SCHOLARSHIPMAJOR")
        detailScholarshipBinding.tvDegreeDetailScholarship.text =
            getIntent().getStringExtra("SCHOLARSHIPDEGREE")
        detailScholarshipBinding.tvDateOpenregistrationDetailscholarship.text =
            getIntent().getStringExtra("SCHOLARSHIPOPENREGISTRATION")
        detailScholarshipBinding.tvDateDeadlineDetailscholarship.text =
            getIntent().getStringExtra("SCHOLARSHIPCLOSEREGRISTRATION")
        detailScholarshipBinding.tvLink.text =
            getIntent().getStringExtra("SCHOLARSHIPLINK")
        detailScholarshipBinding.tvBenefitDetailscholarship.text =
            getIntent().getStringExtra("SCHOLARSHIPBENEFIT")
        detailScholarshipBinding.tvRequirementsDetailscholarship.text =
            getIntent().getStringExtra("SCHOLARSHIPREQUIREMENTS")

        clickablelink(longTextWithLinks)
    }

    private fun clickablelink(longText: String) {
        try {
            val spanned = SpannableString(longText)
            val matcher = Patterns.WEB_URL.matcher(longText)
            var matchStart: Int
            var matchEnd: Int

            while (matcher.find()) {
                matchStart = matcher.start(1)
                matchEnd = matcher.end()

                var url = longText.substring(matchStart, matchEnd)
                if (!url.startsWith("http://") && !url.startsWith("http://"))
                    url = "http://$url"

                val clickableSpan: ClickableSpan = object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        startActivity(intent)
                    }

                    override fun updateDrawState(ds: TextPaint) {
                        super.updateDrawState(ds)
                        ds.color = Color.GREEN
                        ds.isUnderlineText = false
                    }
                }
                spanned.setSpan(
                    clickableSpan,
                    matchStart,
                    matchEnd,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            detailScholarshipBinding.tvLink.text = spanned
            detailScholarshipBinding.tvLink.movementMethod = LinkMovementMethod.getInstance()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}