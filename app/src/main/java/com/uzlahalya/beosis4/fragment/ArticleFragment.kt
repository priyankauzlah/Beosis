package com.uzlahalya.beosis4.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uzlahalya.beosis4.R
import com.uzlahalya.beosis4.databinding.FragmentArticleBinding
import com.uzlahalya.beosis4.adapter.ArticleAdapter
import com.uzlahalya.beosis4.model.ArticleItem
import com.uzlahalya.beosis4.model.ArticleResponse
import com.uzlahalya.beosis4.mvvm.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleFragment : Fragment(), View.OnClickListener {

    private val articleAdapter by lazy {
        ArticleAdapter(requireContext())
    }

    private var articleBinding: FragmentArticleBinding? = null
    private val arBinding get() = articleBinding!!
    lateinit var rvArticle : RecyclerView

    companion object{
            fun articleFragment(): ArticleFragment {
                val article_fragment = ArticleFragment()
                val bundle = Bundle()
                article_fragment.arguments = bundle
                return article_fragment
            }

            @JvmStatic
            fun newInstance() = ArticleFragment().apply {
                arguments = Bundle().apply {}
            }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        articleBinding = FragmentArticleBinding.inflate(inflater, container, false)
        val view = arBinding.root

        val lmArticle = LinearLayoutManager(activity)
        lmArticle.orientation = LinearLayoutManager.VERTICAL

        rvArticle = view.findViewById(R.id.rv_article)

        ApiService.getArticleService().getDataArticle().enqueue(object : Callback<ArticleResponse> {
            override fun onResponse(
                call: Call<ArticleResponse>,
                response: Response<ArticleResponse>
            ) {
//                loading.dismiss()
                Log.d("Data", "Response: ${response.isSuccessful}")

                if (response.isSuccessful) {
                    Log.d("SUCCES", "Success retrieved data")
                    val responseArticle = response.body()
                    val dataArticle = responseArticle?.article
                    articleAdapter.setData(dataArticle as List<ArticleItem>)
                }
            }
            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                Log.d("Error","Error pada : "+t.localizedMessage)
            }
        })

        rvArticle.setHasFixedSize(true)
        rvArticle.layoutManager = lmArticle
        rvArticle.adapter = articleAdapter

        return view
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

//    val ArrayArticle : ArrayList<Article>get(){
//        val arrayArticle = ArrayList<Article>()
//
//        //1
//        val studyinmalaysia = Article()
//        studyinmalaysia.image = R.drawable.study_in_malaysia
//        studyinmalaysia.title = "Study in Malaysia"
//        studyinmalaysia.country = "Malaysia"
//        studyinmalaysia.content = "Why Study in Malaysia?\n" +
//                "Having been known as one of a spectacular tourist destinations, Malaysia is also revered for offering top-notch higher education for international students across the globe. When you consider studying in Malaysia, you witness kick-starting your higher education in Southeast Asia's most multicultural and vibrant environment. The country boasts of its impeccable education system and curriculum with top-ranked aviation, marketing, engineering, and nursing institutions year after year. One of the notable factors is that the Government retains young international talents by offering jobs in critical fields for students demonstrating academic excellence. \n" +
//                "Malaysia is constantly recognised as an education hub for international students. According to UNESCO ranks, Malaysia ranks among the top 20 preferred study destinations globally, accounting for 2% of the total international students population. When it comes to safety for students, the Government takes preventive measures to ensure international students across the country feel secure and stay peacefully at their off-campus accommodation in Malaysia. Considering this fact, Malaysia constantly stays in the top 25 safest countries year after year according to Global Peace Index.\n"
//
//        //2
//        val studyinjapan = Article()
//        studyinjapan.image = R.drawable.study_in_malaysia
//        studyinjapan.title = "Study in Japan"
//        studyinjapan.country = "Japan"
//        studyinjapan.content = "When referring to facts about Japanese culture, multiple things come across our minds. From the cherry blossom trees opening the Sakura season every spring, the colourful classic kimono dresses, the alternative Harajuku Style and Japanese cosplay, to the famous national sport of Sumo wrestling, Karate, Judo, Jiu-Jitsu or calligraphy and origami, the list in unending! \n" +
//                "Japan is a country that has been influenced throughout the centuries by the Chinese dynasties, but has maintained its own unique identity and has given a lot to the contemporary world.\n"
//
//        //3
//        val studyinindonesia = Article()
//        studyinindonesia.image = R.drawable.study_in_malaysia
//        studyinindonesia.title = "Studying Abroad in Indonesia for International Students"
//        studyinindonesia.country = "Indonesia"
//        studyinindonesia.content = "As Indonesia becomes an increasingly important pillar in the global economy given its promising economic outlook, setting it on course to be the world’s 7th largest economy by 2030 and its position as by far the largest economy in ASEAN, it is naturally attracting the interest of international students. To date, Indonesia has less than 6,000 international students studying in local universities which is a paltry figure compared to Malaysia with 63,000 and Singapore with 52,000 (UNESCO, 2014). There are a number of factors at play accounting for the low number of international students studying in Indonesian universities including the quality of Indonesian educational institutions, the lack of student exchange programmes, student visa issues as well as the lack of promotion of the country to international students."
//
//        //4
//        val studyintaiwan = Article()
//        studyintaiwan.image = R.drawable.study_in_malaysia
//        studyintaiwan.title = "Students Prespective"
//        studyintaiwan.country = "Taiwan"
//        studyintaiwan.content = "My name is fanny. I am an overseas Chinese student from Indonesia. I love studying while traveling. Thus, I have traveled to many different countries in my life.\n" +
//                "\n" +
//                "\n" +
//                "1. WHAT MADE YOU DECIDE TO STUDY IN TAIWAN?\n" +
//                "I am in love with Taiwanese culture, environment, and down to earth people of Taiwan. Besides, I want to strengthen my Chinese language ability, which is very important in business world nowadays. Moreover, there is more Taiwanese company invests in Indonesia, which will indeed benefit Indonesian student graduated from Taiwan’s university in their future career path.\n"
//
//        //5
//        val studyincanada = Article()
//        studyincanada.image = R.drawable.study_in_malaysia
//        studyincanada.title = "Study in Canada Overview"
//        studyincanada.country = "Canada"
//        studyincanada.content = "Canada has become a booming hub for international students whose dreams include remaining in the country in which they complete their studies. No longer an overlooked global destination to earn a degree, Canada also offers a direct path to permanent citizenship - an option many international students seek. Canada has ranked as one of the top ten places to live in the world for over twenty years, and boasts an education system ranked among the best.\n" +
//                "Canada is an increasingly popular option for students who seek and require a less expensive alternative to studying internationally than they might find in the US. Canadian universities are now providing equally prestigious degrees as those in the US, often at a more affordable cost.\n"
//
//        arrayArticle.add(studyinmalaysia)
//        arrayArticle.add(studyinjapan)
//        arrayArticle.add(studyinindonesia)
//        arrayArticle.add(studyintaiwan)
//        arrayArticle.add(studyincanada)
//
//        return arrayArticle
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        articleBinding = null
//    }

//    override fun onItemClick(item: Article, position: Int) {
//        val intent = Intent(this.requireContext(), DetailArticleActivity::class.java)
//        intent.putExtra("ARTICLEIMAGE", item.image.toString())
//        intent.putExtra("ARTICLETITLE", item.title)
//        intent.putExtra("ARTICLECOUNTRY", item.country)
//        intent.putExtra("ARTICLECONTENT", item.content)
//
//        startActivity(intent)
//    }
}