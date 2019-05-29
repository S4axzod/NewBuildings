package com.example.buildings.adapters

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.buildings.api.RetrofitClient
import com.example.buildings.databinding.GalleryItemBinding
import com.squareup.picasso.Picasso


class GalleryAdapter(context: Context, array: Array<String>, listener: View.OnClickListener) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>(),
        View.OnClickListener{
    private val inflater: LayoutInflater
    private var context: Context
    private var listener: View.OnClickListener

    var items: Array<String>?

    init {
        this.context = context
        this.listener = listener
        this.items = array
        this.inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val binding = GalleryItemBinding.inflate(inflater, p0, false)
        return PastVH(binding.root)
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val item = items?.get(p1)
        val binding = (p0 as PastVH).binding
        Picasso.get()
            .load(RetrofitClient.BASE_URL + item)
            .into(binding?.imageActual)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    private inner class PastVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: GalleryItemBinding? = null

        init {
            binding = DataBindingUtil.bind(itemView)
        }
    }

    override fun onClick(v: View?) {

    }

}
