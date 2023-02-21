//package com.uzlahalya.beosis4.fragment

//import android.app.ProgressDialog
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
//import androidx.recyclerview.widget.RecyclerView
//import com.google.gson.Gson
//import com.uzlahalya.beosis4.R
//import com.uzlahalya.beosis4.activity.DetailScholarshipActivity
//import com.uzlahalya.beosis4.adapter.ScholarshipAdapter
//import com.uzlahalya.beosis4.data.MostPopularAdapter
//import com.uzlahalya.beosis4.data.Scholarship
//import com.uzlahalya.beosis4.model.ScholarshipResponse
//import com.uzlahalya.beosis4.util.ApiService
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class HomeFragment : Fragment(), View.OnClickListener{
//
////    private var homeBinding: HomeFragment? = null

//
//    private lateinit var scholarshipAdapter: ScholarshipAdapter
//    private lateinit var rvScholarship : RecyclerView
//
//    companion object{
//        fun homeFragment(): HomeFragment{
//            val home_fragment = HomeFragment()
//            val bundle = Bundle()
//            home_fragment.arguments = bundle
//            return home_fragment
//        }
//
//        @JvmStatic
//        fun newInstance() = HomeFragment().apply {
//            arguments = Bundle().apply { }
//        }
//    }
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        val v: View = inflater.inflate(R.layout.fragment_home, container, false)
//        scholarshipAdapter = ScholarshipAdapter(requireContext())
//        rvScholarship = v.findViewById(R.id.rv_explore_more)
//        rvScholarship.apply {
//            this.adapter = scholarshipAdapter
//            this.layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
//            this.setHasFixedSize(true)
//        }
//
//        return v
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        getDatas()
//    }
//
//    private fun getDatas() {
//        val loading = ProgressDialog.show(context, "Request Loading", "Loading...")
//        ApiService.endpoint.getData().enqueue(
//            object : Callback<ScholarshipResponse> {
//                override fun onResponse(call: Call<ScholarshipResponse>, response: Response<ScholarshipResponse>){
//                    loading.dismiss()
//                    Log.d("DATA", "hide loading")
//                    Log.d("DATA", "RESPONSE: ${response.isSuccessful}")
//                    if (response.isSuccessful) {
//                        val data = response.body()
//
//                        Log.d("DATA", "success")
//                        if (data?.status == "success") {
//                            Log.d("DATA", 200.toString())
//                            if (!data.scholarship.isNullOrEmpty()) {
//                                Log.d("DATA", "ADA")
//                                Log.d("DATA", Gson().toJson(data.scholarship))
//                                scholarshipAdapter.setData(data.scholarship)
//                            }
//                        }
//                    }
//                }
//                override fun onFailure(call: Call<ScholarshipResponse>, t: Throwable) {
//                    Log.d("Response", "Failed : " + t.localizedMessage)
//                    loading.dismiss()
//                }
//            }
//        )
//    }
//
//    override fun onClick(p0: View?) {
//        TODO("Not yet implemented")
//    }
//
//}
    package com.uzlahalya.beosis4.fragment

    import android.content.Intent
    import android.os.Bundle
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.fragment.app.Fragment
    import androidx.recyclerview.widget.LinearLayoutManager
    import androidx.recyclerview.widget.RecyclerView
    import com.uzlahalya.beosis4.R
    import com.uzlahalya.beosis4.activity.DetailScholarshipActivity
    import com.uzlahalya.beosis4.adapter.ScholarshipAdapter
    import com.uzlahalya.beosis4.data.MostPopularAdapter
    import com.uzlahalya.beosis4.data.Scholarship
    import com.uzlahalya.beosis4.data.ScholarshipAdapterr

class HomeFragment : Fragment(), ScholarshipAdapterr.onScholarshipItemClickListener,
        MostPopularAdapter.onMostPopularItemClickListener {

        private var homeBinding: HomeFragment? = null
        lateinit var rvExploreMore: RecyclerView
        lateinit var rvMostPopular: RecyclerView


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            val view = inflater.inflate(R.layout.fragment_home, container, false)

            val lmExploreMore = LinearLayoutManager(activity)
            lmExploreMore.orientation = LinearLayoutManager.VERTICAL

            val lmMostPopular = LinearLayoutManager(activity)
            lmMostPopular.orientation = LinearLayoutManager.HORIZONTAL

            rvExploreMore = view.findViewById(R.id.rv_explore_more)
            rvMostPopular = view.findViewById(R.id.rv_most_popular)

            val adapterScholarship = ScholarshipAdapterr(ArrayScholarship, activity, this)
            rvExploreMore.setHasFixedSize(true)
            rvExploreMore.layoutManager = lmExploreMore
            rvExploreMore.adapter = adapterScholarship

            val adapterMostPopular = ScholarshipAdapterr(ArrayMostPopularScholarship, activity, this)
            rvMostPopular.setHasFixedSize(true)
            rvMostPopular.layoutManager = lmMostPopular
            rvMostPopular.adapter = adapterMostPopular

            return view
        }

        val ArrayScholarship: ArrayList<Scholarship>
            get() {
                val arrayScholarship = ArrayList<Scholarship>()

                //1
                val china_aun = Scholarship()
                china_aun.logo = R.drawable.china_aun
                china_aun.name = "China AUN Scholarship"
                china_aun.university = "Selected host university"
                china_aun.degree = "Graduate"
                china_aun.country = "China"
                china_aun.majors =
                    "Philosophy, Economics, Legal Studies, Education, Literature, History, and Management, Science, Engineering, and Agriculture, and Medicine."
                china_aun.openregistration = "-"
                china_aun.link =
                    "https://www.aunsec.org/application/files/6816/4016/9731/China-AUN_Scholarship_2022_Application_Guidelines.pdf"
                china_aun.closeregistration = "2023-01-31"
                china_aun.benefit = "- Tuition Waiver: Tuition funds will be comprehensively used by the host university. It may cover scholarship students’ education, administration costs and expenditures to support student activities. \n" +
                        "- Accommodation: free university dormitory or accommodation subsidy. If the host university requires students to live on campus, the university will accommodate the scholarship students in a university dormitory (usually a twin room); if the host university permits students to live off campus, the university will provide monthly/quarterly accommodation subsidy:\n" +
                        "- Master’s students: CNY 700 per month; \n" +
                        "- Doctoral students: CNY 1,000 per month.\n"
                china_aun.requirement = "- Applicants must be a citizen of an ASEAN member country (Brunei, Cambodia, Indonesia, Laos, Malaysia, Myanmar, Philippines, Singapore, Thailand and Vietnam), and be in good health.\n" +
                        "- Applicants must be: - A bachelor’s degree holder under the age of 35 when applying for the master’s programs; - A master’s degree holder under the age of 40 when applying for the doctoral programs.\n" +
                        "- Acquired IELTS or TOEFL when applying for English taught master’s or doctoral programs;\n" +
                        "- Acquired the Chinese proficiency of HSK Level 4 when applying for Chinese taught master’s or doctoral programs. \n"

                //2
                val mext = Scholarship()
                mext.logo = R.drawable.mext
                mext.name = "MEXT"
                mext.university =
                    "Kyoto University, Ritsumeikan Asia Pacific University, The university of Tokyo"
                mext.degree = "Undergraduate"
                mext.country = "Japan"
                mext.majors =
                    "Japanese, mathematics, English, and social sciences (for those who choose the field of social studies), and physics, chemistry, biology (for those who choose the field of science)"
                mext.link =
                    "https://www.aunsec.org/application/files/6816/4016/9731/China-AUN_Scholarship_2022_Application_Guidelines.pdf"
                mext.openregistration = "20 Dec 2023"
                mext.closeregistration = "2024-01-31"
                mext.benefit = "- Tuition fees are fully covered (including preparatory school fees)\n" +
                        "- Living allowance of approximately 117,000/month\n" +
                        "- Return flight ticket Indonesia - Japan\n" +
                        "- Free student visa processing fees\n" +
                        "- No official ties\n"
                mext.requirement = "-High school/vocational high school graduates and equivalent.\n" +
                        "-Maximum age 24 years when applying\n" +
                        "-Minimum of 84 for each subject in each semester; Mathematics is mandatory and English is mandatory.\n" +
                        "-For applicants who choose the field of study in the Science category, they cannot register if the average value of the subjects mentioned is less than 84, even though they have a JLPT certificate.\n" +
                        "-For participants who choose the field of study in the Social Sciences category if one of the subjects mentioned is less than 84, they can still register by attaching a certificate:\n" +
                        "Japanese Language Proficiency Test (JLPT) minimum level N3\n" +
                        "-Minimum EJU 240 points for Japanese test\n" +
                        "-For applicants who have EJU scores in any subject will be an additional point.\n"

                //3
                val sophia = Scholarship()
                sophia.logo = R.drawable.sophia_university_new_students
                sophia.name = "Sophia University New Students Scholarship"
                sophia.university = "Japan"
                sophia.degree = "Undergraduate & Graduate"
                sophia.country = "Sophia University"
                sophia.majors =
                    "Engineering, Humanities and Social Sciences, Finance and Economics, Business."
                sophia.link =
                    "https://www.aunsec.org/application/files/6816/4016/9731/China-AUN_Scholarship_2022_Application_Guidelines.pdf"
                sophia.openregistration = "17 Nov 2023"
                sophia.closeregistration = "13 Apr 2024"
                sophia.benefit = "-The amount equal to full tuition (annual) \n" +
                        "- The amount equal to half tuition(annual) \n" +
                        "- The amount equal to one-third tuition (annual) \n"
                sophia.requirement = "- Ijazah SMA\n" +
                        "- Passport\n" +
                        "- Essay 1\n" +
                        "- Application Form\n" +
                        "- Recommendation Letters\n" +
                        "- Proof of English Proficiency\n" +
                        "- Academic Transcript\n" +
                        "- Standardized Tests\n" +
                        "- Checklist\n"

                //4
                val mitsui = Scholarship()
                mitsui.logo = R.drawable.mitsui_bussan
                mitsui.name = "Mitsui Bussan Scholarship"
                mitsui.university = "Any universities in Japan"
                mitsui.country = "Japan"
                mitsui.degree = "Undergraduate"
                mitsui.majors =
                    "Study of Sciences and Engineering & Study of Social Sciences, Business and Commerce\n"
                mitsui.link =
                    "https://www.aunsec.org/application/files/6816/4016/9731/China-AUN_Scholarship_2022_Application_Guidelines.pdf"
                mitsui.openregistration = "2023-01-17"
                mitsui.closeregistration = "2023-02-18"
                mitsui.benefit = "- 145,000 Japanese yen per month\n" +
                        "- Transportation Charge\n" +
                        "- Arrival Allowance\n" +
                        "- School Fees\n" +
                        "- Accommodations, Meals, Local Transportation and Other Costs and Expenses\n"
                mitsui.requirement = "- Nationality: Applicants must be citizens of the Republic of Indonesia.\n" +
                        "- Age: Applicants must be less than twenty (20) years of age as of April 1st, 2023 and unmarried.\n" +
                        "- Academic Background and School Records: Applicants must have completed SMA (Senior High School) of IPA or IPS course with marks of at least eighty (80) or higher on their school records for the following subjects\n" +
                        "- Application Form\n" +
                        "- Recommendation Letters\n" +
                        "- National exams\n"

                //5
                val asean = Scholarship()
                asean.logo = R.drawable.asean_undergraduate
                asean.name = "Asean Undergraduate Scholarship"
                asean.university = "National University Singapore"
                asean.country = "Singapore"
                asean.degree = "Undergraduate"
                asean.link =
                    "https://www.aunsec.org/application/files/6816/4016/9731/China-AUN_Scholarship_2022_Application_Guidelines.pdf"
                asean.majors = "all majors excluding Dentistry, Law, Medicine, and Music"
                asean.openregistration = "15 Oct 2023"
                asean.closeregistration = "14 Feb 2024"
                asean.benefit = "-Tuition fees (after MOE Tuition Grant Subsidy)\n" +
                        "-S\$5,800 annual living allowance\n"
                asean.requirement = "- Be citizens of an ASEAN* member country, excluding Singapore\n" +
                        "- Have strong leadership qualities and potential\n" +
                        "- Present good co-curricular activities records\n" +
                        "Possess outstanding high school results\n" +
                        "- Be applying for admission to a full-time undergraduate degree programme at NUS\n"

                arrayScholarship.add(china_aun)
                arrayScholarship.add(mext)
                arrayScholarship.add(sophia)
                arrayScholarship.add(mitsui)
                arrayScholarship.add(asean)

                return arrayScholarship
            }

        val ArrayMostPopularScholarship: ArrayList<Scholarship>
            get() {
                val arrayMostPopularScholarship = ArrayList<Scholarship>()

                //1
                val mitsui = Scholarship()
                mitsui.logo = R.drawable.mitsui_bussan
                mitsui.name = "Mitsui Bussan Scholarship"
                mitsui.university = "Any universities in Japan"
                mitsui.degree = "Undergraduate"
                mitsui.country = "Japan"
                mitsui.majors =
                    "Study of Sciences and Engineering & Study of Social Sciences, Business and Commerce\n"
                mitsui.link =
                    "https://www.aunsec.org/application/files/6816/4016/9731/China-AUN_Scholarship_2022_Application_Guidelines.pdf"
                mitsui.openregistration = "2023-01-17"
                mitsui.closeregistration = "2023-02-18"
                mitsui.benefit = "- 145,000 Japanese yen per month\n" +
                        "- Transportation Charge\n" +
                        "- Arrival Allowance\n" +
                        "- School Fees\n" +
                        "- Accommodations, Meals, Local Transportation and Other Costs and Expenses\n"
                mitsui.requirement = "- Nationality: Applicants must be citizens of the Republic of Indonesia.\n" +
                        "- Age: Applicants must be less than twenty (20) years of age as of April 1st, 2023 and unmarried.\n" +
                        "- Academic Background and School Records: Applicants must have completed SMA (Senior High School) of IPA or IPS course with marks of at least eighty (80) or higher on their school records for the following subjects\n" +
                        "- Application Form\n" +
                        "- Recommendation Letters\n" +
                        "- National exams\n"

                //2
                val australia = Scholarship()
                australia.logo = R.drawable.australia_awards_scholarship
                australia.name = "Australia Awards Scholarship"
                australia.university = "Any universities in Australia"
                australia.degree = "Post Graduate"
                australia.country = "Australia"
                australia.majors =
                    "All majors"
                australia.link =
                    "https://www.australiaawardsindonesia.org/"
                australia.openregistration = "2023-02-14"
                australia.closeregistration = "2023-05-01"
                australia.benefit = "- return air travel\n" +
                        "- a one off establishment allowance on arrival\n" +
                        "- full tuition fees\n" +
                        "- contribution to living expenses\n" +
                        "- introductory academic program\n" +
                        "- overseas student health cover for the duration of the\n" +
                        "- supplementary academic support,\n" +
                        "- fieldwork allowance for research students and masters\n" +
                        "- by coursework which has a compulsory fieldwork\n"
                australia.requirement = "- Max 100 years old\n" +
                        "- Min. GPA 2.94 out of 4.00\n" +
                        "- Wajib memenuhi salah satu:\n" +
                        "• IELTS (6.5)\n" +
                        "• TOEFL iBT (84)\n" +
                        "- Citizenship Document (KTP/KK/Akta)\n" +
                        "- Research Proposal\n" +
                        "- S2 - Certificate of Graduate\n" +
                        "- Passport\n" +
                        "- Curriculum Vitae\n" +
                        "- Academic Transcript\n" +
                        "- Photocopy of valid TOEFL/IELTS/GRE/GMAT/GATE Scores\n"

                //3
                val ioe = Scholarship()
                ioe.logo = R.drawable.ioe_centenary
                ioe.name = "IOE Centenary Master’s Scholarship"
                ioe.university = "University College London"
                ioe.degree = "Post Graduate"
                ioe.country = "United Kingdom"
                ioe.majors =
                    "All except MA Educational Neuroscience"
                ioe.link =
                    "https://www.ucl.ac.uk/ioe/about-ioe/global-reach/scholarships-and-funding#eligible-countries"
                ioe.openregistration = "-"
                ioe.closeregistration = "2023-05-02"
                ioe.benefit = "-Scholarships will cover full tuition fees \n" +
                        "-Accommodation at International Students House for one academic year.\n"
                ioe.requirement = "- lower-middle income country according to the World Bank classification (Indonesia)\n" +
                        "- have an official unconditional offer to study a full time master's degree at IOE (letter issued by UCL Admissions)\n" +
                        "- Do not have studied or lived in the UK before.\n"

                arrayMostPopularScholarship.add(mitsui)
                arrayMostPopularScholarship.add(australia)
                arrayMostPopularScholarship.add(ioe)

                return arrayMostPopularScholarship
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
            intent.putExtra("SCHOLARSHIPBENEFIT", item.benefit)
            intent.putExtra("SCHOLARSHIPREQUIREMENTS", item.requirement)

            startActivity(intent)
        }

    }
