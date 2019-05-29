package com.example.buildings.ui.News

import android.app.Application
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.buildings.adapters.NewsAdapter
import com.example.buildings.databinding.FragmentMediaBinding
import com.example.buildings.model.News
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MediaFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentMediaBinding
    val vm: NewsVM by viewModel()

    lateinit var newsAdapter: NewsAdapter
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMediaBinding.inflate(layoutInflater)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        newsAdapter = NewsAdapter(context!!, ArrayList(), this)

        binding.recyclerNews.layoutManager = layoutManager
        binding.recyclerNews.adapter = newsAdapter

        vm.news.observe(this, object : Observer<List<News>> {
            override fun onChanged(t: List<News>?) {
                newsAdapter.items = t!!
                newsAdapter.notifyDataSetChanged()
            }
        })

        return binding.root
    }

    override fun onClick(v: View?) {

    }

}