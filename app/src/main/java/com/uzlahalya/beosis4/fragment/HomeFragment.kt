package com.uzlahalya.beosis4.fragment

import ScholarshipItem
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.play.core.internal.t
import com.uzlahalya.beosis4.R
import com.uzlahalya.beosis4.activity.DetailMostPopularActivity
import com.uzlahalya.beosis4.adapter.MostPopularAdapter
import com.uzlahalya.beosis4.adapter.ScholarshipAdapter
import com.uzlahalya.beosis4.data.Scholarship
import com.uzlahalya.beosis4.databinding.FragmentHomeBinding
import com.uzlahalya.beosis4.model.ArticleItem
import com.uzlahalya.beosis4.model.ArticleResponse
import com.uzlahalya.beosis4.model.ScholarshipResponse
import com.uzlahalya.beosis4.mvvm.ApiService
import com.uzlahalya.beosis4.mvvm.network.handler.NetworkResult
import com.uzlahalya.beosis4.viewmodel.MainViewModel
import okhttp3.Callback
import retrofit2.Call
import retrofit2.Response
import java.util.*

class HomeFragment : Fragment(), View.OnClickListener {

    private val scholarAdapter by lazy {
        ScholarshipAdapter(this)
    }

    private lateinit var rvScholarship: RecyclerView
    private var homeBinding: FragmentHomeBinding? = null
    private val binding get() = homeBinding!!
    lateinit var rvMostPopular: RecyclerView
    lateinit var rvExploreMore: RecyclerView
    private val mainViewModel by viewModels<MainViewModel>()

    companion object {
        fun homeFragment(): HomeFragment {
            val home_fragment = HomeFragment()
            //ngirim ke oncreate
            val bundle = Bundle()
            //arguments default function u ngirim data
            home_fragment.arguments = bundle
            return home_fragment
        }

        @JvmStatic
        fun newInstance() = HomeFragment().apply {
            arguments = Bundle().apply {}
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        val lmMostPopular = LinearLayoutManager(activity)
        lmMostPopular.orientation = LinearLayoutManager.HORIZONTAL

        val lmExploreMore = LinearLayoutManager(activity)
        lmExploreMore.orientation = LinearLayoutManager.VERTICAL

        rvMostPopular = view.findViewById(R.id.rv_most_popular)

        val adapterMostPopular = MostPopularAdapter(
            ArrayMostPopularScholarship,
            activity,
            object : MostPopularAdapter.onMostPopularItemClickListener {
                override fun onItemClick(item: Scholarship, position: Int) {
                    val intent = Intent(context, DetailMostPopularActivity::class.java)
                    intent.putExtra(DetailMostPopularActivity.EXTRA_MOST_POPULAR, item)
                    startActivity(intent)
                }
            })

        rvMostPopular.setHasFixedSize(true)
        rvMostPopular.layoutManager = lmMostPopular
        rvMostPopular.adapter = adapterMostPopular

        rvExploreMore = view.findViewById(R.id.rv_explore_more)

//        mainViewModel.scholarshipList.observe(viewLifecycleOwner) { result ->
//            when (result) {
//                is NetworkResult.Loading -> {
//                    handleUi(
//                        recyclerView = false,
//                        progressbar = true,
//                        errorTv = false
//                    )
//                }
//                is NetworkResult.Error -> {
//                    binding.tvError.text = result.errorMessage
//                    handleUi(
//                        recyclerView = false,
//                        progressbar = false,
//                        errorTv = true
//                    )
//                    Toast.makeText(requireContext(),result.errorMessage,Toast.LENGTH_SHORT).show()
//                }
//                is NetworkResult.Success -> {
//                    val scholarshipAdapter = ScholarshipAdapter(this)
//                    Log.d("SUCCESS", "Success retrivied data")
//                    scholarshipAdapter.setData(result.data?.scholarship as List<ScholarshipItem>)
//
//                    binding.rvExploreMore.apply {
//                        layoutManager = LinearLayoutManager(requireContext())
//                        setHasFixedSize(true)
//                        adapter = scholarshipAdapter
//                    }
//                    handleUi(recyclerView = true, progressbar = false, errorTv = false)
//                }
//            }
//        }

        ApiService.getService().getDataScholarship().enqueue(object : retrofit2.Callback<ScholarshipResponse> {
            override fun onResponse(
                call: Call<ScholarshipResponse>,
                response: Response<ScholarshipResponse>
            ) {
                Log.d("Data", "Response: ${response.isSuccessful}")

                if (response.isSuccessful) {
                    Log.d("SUCCESS", "Success retrieved data")
                    val responseScholar = response.body()
                    val dataScholar = responseScholar?.scholarship
                    scholarAdapter.setData(dataScholar as List<ScholarshipItem>)
                }
            }

            override fun onFailure(call: Call<ScholarshipResponse>, t: Throwable) {
                Log.d("Error","Error pada : "+t.localizedMessage)
            }

        })

        rvExploreMore.setHasFixedSize(true)
        rvExploreMore.layoutManager = lmExploreMore
        rvExploreMore.adapter = scholarAdapter

        return view
    }

    private fun handleUi(recyclerView: Boolean, progressbar: Boolean, errorTv: Boolean) {
        binding.apply {
            rvExploreMore.isVisible = recyclerView
            pbHome.isVisible = progressbar
            tvError.isVisible = errorTv
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
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
            "https://www.mbkscholarship-id.com/japan-scholarship-prospectus-2022/"
        mitsui.openregistration = "2023-01-17"
        mitsui.closeregistration = "2023-02-18"
        mitsui.benefit = "- 145,000 Japanese yen per month\n" +
                "- Transportation Charge\n" +
                "- Arrival Allowance\n" +
                "- School Fees\n" +
                "- Accommodations, Meals, Local Transportation and Other Costs and Expenses\n"
        mitsui.requirement =
            "- Nationality: Applicants must be citizens of the Republic of Indonesia.\n" +
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
        ioe.requirement =
            "- lower-middle income country according to the World Bank classification (Indonesia)\n" +
                    "- have an official unconditional offer to study a full time master's degree at IOE (letter issued by UCL Admissions)\n" +
                    "- Do not have studied or lived in the UK before.\n"

        arrayMostPopularScholarship.add(mitsui)
        arrayMostPopularScholarship.add(australia)
        arrayMostPopularScholarship.add(ioe)

        return arrayMostPopularScholarship
    }