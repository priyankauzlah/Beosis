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
}