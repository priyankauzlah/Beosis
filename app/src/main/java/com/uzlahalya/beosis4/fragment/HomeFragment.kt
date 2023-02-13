package com.uzlahalya.beosis4.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uzlahalya.beosis4.R
import com.uzlahalya.beosis4.activity.DetailScholarshipActivity
import com.uzlahalya.beosis4.datascholar.ScholarshipAdapter
import com.uzlahalya.beosis4.datascholar.Scholarship

class HomeFragment : Fragment(), ScholarshipAdapter.onScholarshipItemClickListener  {

    private var homeBinding : HomeFragment? = null
    lateinit var rvExploreMore : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val lm = LinearLayoutManager(activity)
        lm.orientation = LinearLayoutManager.VERTICAL
        rvExploreMore = view.findViewById(R.id.rv_explore_more)

        val adapterChat = ScholarshipAdapter(ArrayScholarship, activity, this)
        rvExploreMore.setHasFixedSize(true)
        rvExploreMore.layoutManager = lm
        rvExploreMore.adapter = adapterChat

        return view
    }

    val ArrayScholarship : ArrayList<Scholarship>get(){
        val arrayScholarship = ArrayList<Scholarship>()

        //1
        val china_aun = Scholarship()
        china_aun.logo = R.drawable.china_aun
        china_aun.name = "China AUN Scholarship"
        china_aun.university = "Selected host university"
        china_aun.degree = "Graduate"
        china_aun.country = "China"
        china_aun.majors = "Philosophy, Economics, Legal Studies, Education, Literature, History, and Management, Science, Engineering, and Agriculture, and Medicine."
        china_aun.openregistration = "-"
        china_aun.link = "https://www.aunsec.org/application/files/6816/4016/9731/China-AUN_Scholarship_2022_Application_Guidelines.pdf"
        china_aun.closeregistration = "2023-01-31"

        //2
        val mext = Scholarship()
        mext.logo = R.drawable.mext
        mext.name = "MEXT"
        mext.university = "Kyoto University, Ritsumeikan Asia Pacific University, The university of Tokyo"
        mext.degree = "Undergraduate"
        mext.country = "Japan"
        mext.majors = "Japanese, mathematics, English, and social sciences (for those who choose the field of social studies), and physics, chemistry, biology (for those who choose the field of science)"
        mext.link = "https://www.aunsec.org/application/files/6816/4016/9731/China-AUN_Scholarship_2022_Application_Guidelines.pdf"
        mext.openregistration = "20 Dec 2023"
        mext.closeregistration = "2024-01-31"

        //3
        val sophia = Scholarship()
        sophia.logo = R.drawable.sophia_university_new_students
        sophia.name = "Sophia University New Students Scholarship"
        sophia.university = "Japan"
        sophia.degree = "Undergraduate & Graduate"
        sophia.country = "Sophia University"
        sophia.majors = "Engineering, Humanities and Social Sciences, Finance and Economics, Business."
        sophia.link = "https://www.aunsec.org/application/files/6816/4016/9731/China-AUN_Scholarship_2022_Application_Guidelines.pdf"
        sophia.openregistration = "17 Nov 2023"
        sophia.closeregistration = "13 Apr 2024"

        //4
        val mitsui = Scholarship()
        mitsui.logo = R.drawable.mitsui_bussan
        mitsui.name = "Mitsui Bussan Scholarship"
        mitsui.university = "Any universities in Japan"
        mitsui.country = "Japan"
        mitsui.degree = "Undergraduate"
        mitsui.majors = "Study of Sciences and Engineering & Study of Social Sciences, Business and Commerce\n"
        mitsui.link = "https://www.aunsec.org/application/files/6816/4016/9731/China-AUN_Scholarship_2022_Application_Guidelines.pdf"
        mitsui.openregistration = "2023-01-17"
        mitsui.closeregistration = "2023-02-18"

        //5
        val asean = Scholarship()
        asean.logo = R.drawable.asean_undergraduate
        asean.name = "Asean Undergraduate Scholarship"
        asean.university = "National University Singapore"
        asean.country = "Singapore"
        asean.degree = "Undergraduate"
        asean.link = "https://www.aunsec.org/application/files/6816/4016/9731/China-AUN_Scholarship_2022_Application_Guidelines.pdf"
        asean.majors = "all majors excluding Dentistry, Law, Medicine, and Music"
        asean.openregistration = "15 Oct 2023"
        asean.closeregistration = "14 Feb 2024"

        arrayScholarship.add(china_aun)
        arrayScholarship.add(mext)
        arrayScholarship.add(sophia)
        arrayScholarship.add(mitsui)
        arrayScholarship.add(asean)

        return arrayScholarship
    }

    override fun onDestroyView() {
        super.onDestroyView()
        homeBinding = null
    }

    override fun onItemClick(item: Scholarship, position: Int) {
        val intent = Intent(this.requireContext(), DetailScholarshipActivity::class.java)
        intent.putExtra("SCHOLARSHIPLOGO", item.logo.toString())
        intent.putExtra("SCHOLARSHIPNAME", item.name)
        intent.putExtra("SCHOLARSHIPUNIVERSITY", item.university)
        intent.putExtra("SCHOLARSHIPCOUNTRY", item.country)
        intent.putExtra("SCHOLARSHIPMAJOR", item.majors)
        intent.putExtra("SCHOLARSHIPDEGREE", item.degree)
        intent.putExtra("SCHOLARSHIPLINK", item.link)
        intent.putExtra("SCHOLARSHIPOPENREGISTRATION", item.openregistration)
        intent.putExtra("SCHOLARSHIPCLOSEREGRISTRATION", item.closeregistration)

        startActivity(intent)
    }

}