package com.example.buildings.adapters

import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.buildings.api.RetrofitClient
import com.squareup.picasso.Picasso
import com.example.buildings.databinding.PartnersItemBinding
import com.example.buildings.model.Partner


class PartnersAdapter(context: Context, list: List<Partner>, listener: View.OnClickListener) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>(),
        View.OnClickListener {
    private val inflater: LayoutInflater
    private var context: Context
    private lateinit var m300: Typeface
    private lateinit var m500: Typeface
    private lateinit var m700: Typeface

    var items: List<Partner>? = ArrayList()

    private var listener: View.OnClickListener

    init {
        this.context = context
        this.listener = listener
        this.items = list
        m300 = Typeface.createFromAsset(context.getAssets(), "fonts/museo_sans_cyrl_300.ttf")
        m500 = Typeface.createFromAsset(context.getAssets(), "fonts/museo_sans_cyrl_500.ttf")
        m700 = Typeface.createFromAsset(context.getAssets(), "fonts/museo_sans_cyrl_700.ttf")

        this.inflater = LayoutInflater.from(context)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = PartnersItemBinding.inflate(inflater, parent, false)
        return PastVH(binding.getRoot())
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items?.get(position)
        val binding = (holder as PastVH).binding
        binding?.partners = items!!.get(position)
        //binding?.published?.typeface = m300
       // binding?.title?.typeface = m500;
        //binding?.category?.typeface = m700;
        binding!!.partners = item
        Picasso.get()
                .load(RetrofitClient.BASE_URL+item?.photo)
                .into(binding.imageActual)

    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    private inner class PastVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: PartnersItemBinding? = null

        init {
            binding = DataBindingUtil.bind(itemView)
        }
    }

    override fun onClick(p0: View?) {

    }

}


