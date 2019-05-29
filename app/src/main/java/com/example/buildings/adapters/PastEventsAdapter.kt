package com.example.buildings.adapters

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Typeface
import android.os.Parcelable
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.buildings.Const
import com.example.buildings.R
import com.example.buildings.api.RetrofitClient
import com.example.buildings.model.Event
import com.squareup.picasso.Picasso
import com.example.buildings.databinding.EventItemBinding
import com.example.buildings.model.Building
import com.example.buildings.ui.BookingFragment
import com.example.buildings.ui.Feeds.BuildingViewActivity


class PastEventsAdapter(context: Context, list: List<Building>, listener: View.OnClickListener) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>(),
        View.OnClickListener {
    private val inflater: LayoutInflater
    private var context: Context
    private lateinit var m300: Typeface
    private lateinit var m500: Typeface
    private lateinit var m700: Typeface

    var items: List<Building>? = ArrayList()

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
        val binding = EventItemBinding.inflate(inflater, parent, false)
        return PastVH(binding.getRoot())
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items?.get(position)
        val binding = (holder as PastVH).binding
        binding?.buildings = items!!.get(position)
        //binding?.published?.typeface = m300
        binding?.title?.typeface = m500;
        //binding?.category?.typeface = m700;
        binding!!.buildings = item
        Picasso.get()
            .load(RetrofitClient.BASE_URL + item?.photoBackground).fit()
            .into(binding.imageActual)
        Picasso.get()
            .load(RetrofitClient.BASE_URL + item?.organizationLogo)
            .into(binding.logo)

        binding?.containerEvent.setTag(item)
        binding?.containerEvent.setOnClickListener(this)

    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    private inner class PastVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: EventItemBinding? = null

        init {
            binding = DataBindingUtil.bind(itemView)
        }
    }
    private fun logger(msg: String) {
        Log.d("camaro", msg)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.container_event->{
                logger("Container clicked")
                val item = p0?.getTag() as Building
                val intent: Intent = Intent(context, BuildingViewActivity::class.java)
                intent.putExtra(Const.EVENT_EXTRA, item)
                context.startActivity(intent)
            }
        }
    }
}











