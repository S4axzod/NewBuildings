package com.example.buildings.adapters

import android.content.Context
import android.graphics.Typeface
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.buildings.api.RetrofitClient
import com.example.buildings.model.Event
import com.squareup.picasso.Picasso
import com.example.buildings.databinding.CurrentEventItemBinding

class ViewPagerAdapter(context: Context, items: List<Event>) : PagerAdapter() {

    var events: List<Event> = ArrayList<Event>()
    private lateinit var layoutInflater: LayoutInflater
    private lateinit var context: Context
    private lateinit var font: Typeface

    init {
        this.events = items
        this.context = context
        font = Typeface.createFromAsset(context.getAssets(), "fonts/museo_sans_cyrl_500.ttf")
        layoutInflater = LayoutInflater.from(context)
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0.equals(p1)
    }

    override fun getCount(): Int {
        return events.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding: CurrentEventItemBinding = CurrentEventItemBinding.inflate(layoutInflater, container, false)
        val event = events.get(position)
        binding.event = event
        binding.title.typeface = font
        Picasso.get().load(RetrofitClient.BASE_URL +event.photo).into(binding.imageActual)
        container.addView(binding.root, 0)
        return binding.root

    }

    override fun destroyItem(container: ViewGroup, position: Int, a: Any) {
        container.removeView(a as View)
    }
}