package com.example.buildings.ui.Feeds

import android.arch.lifecycle.Observer
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.buildings.App
import com.example.buildings.adapters.PastEventsAdapter
import com.example.buildings.adapters.ViewPagerAdapter
import com.example.buildings.databinding.FragmentFeedsBinding
import com.example.buildings.model.Building
import com.example.buildings.model.Event
import com.example.buildings.model.News
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.ArrayList

class FeedsFragment : Fragment(), View.OnClickListener{

    private lateinit var binding: FragmentFeedsBinding
    val vm: FeedsVM by viewModel()

    lateinit var featured_adapter: ViewPagerAdapter
    lateinit var pastAdapter: PastEventsAdapter
    lateinit var layoutManager2: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFeedsBinding.inflate(layoutInflater)

        layoutManager2 = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        featured_adapter = ViewPagerAdapter(context!!, ArrayList())
        pastAdapter =PastEventsAdapter(context!!, ArrayList(), this)

        binding.viewpager.adapter = featured_adapter
        binding.dotsIndicator.setViewPager(binding.viewpager)

        binding.recyclerPast.layoutManager = layoutManager2
        binding.recyclerPast.adapter = pastAdapter


        binding.viewpager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {

            }
        })
        //viewmodel configuration to observe
        vm.featured.observe(this, object : Observer<List<Event>> {
            override fun onChanged(t: List<Event>?) {
                featured_adapter.events = t!!
                featured_adapter.notifyDataSetChanged()
            }
        })

        vm.building.observe(this, object : Observer<List<Building>> {
            override fun onChanged(t: List<Building>?) {
                pastAdapter.items = t!!
                pastAdapter.notifyDataSetChanged()
            }
        })

        return binding.root
    }

    override fun onClick(v: View?) {

    }
}