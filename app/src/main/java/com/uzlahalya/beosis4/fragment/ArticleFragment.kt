package com.uzlahalya.beosis4.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.uzlahalya.beosis4.R
import com.uzlahalya.beosis4.adapter.ArticleAdapter
import com.uzlahalya.beosis4.databinding.FragmentArticleBinding
import com.uzlahalya.beosis4.model.ArticleItem
import com.uzlahalya.beosis4.model.ArticleResponse
import com.uzlahalya.beosis4.util.observeLiveData
import com.uzlahalya.beosis4.viewmodel.MainViewModel
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
    private var selectedCountry = ""

    private val mainViewModel by viewModels<MainViewModel>()

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
        val chipGroup: ChipGroup = view.findViewById(R.id.chips_continentsType)

        mainViewModel.articleList.observeLiveData(
            owner = viewLifecycleOwner,
            context = requireContext(),
            onSuccess = {
                // TODO: Hide Loading
                if (it.isNotEmpty() && selectedCountry.isNotEmpty()) {
                    articleAdapter.filter.filter(selectedCountry)
                }

                articleAdapter.setData(it)
            },
            onLoading = {
                // TODO: Show Loading
            },
            onFailure = {
                // TODO: Hide Loading
            }
        )

        rvArticle.setHasFixedSize(true)
        rvArticle.layoutManager = lmArticle
        rvArticle.adapter = articleAdapter

        chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip: Chip = chipGroup.findViewById(checkedId)
            Log.d("CHIP", chip.text.toString())
            selectedCountry = chip.text.toString()
            mainViewModel.fetchArticles()
        }

        return view
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}