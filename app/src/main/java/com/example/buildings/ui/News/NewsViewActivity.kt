package com.example.buildings.ui.News

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.buildings.Const
import com.example.buildings.R
import com.example.buildings.api.RetrofitClient
import com.example.buildings.model.News
import com.example.buildings.databinding.ActivityNewsViewBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_news_view.*


class NewsViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_view)

        if(intent == null)
            finish()

        val item: News = intent.getSerializableExtra(Const.EVENT_EXTRA) as News

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        Picasso.get().load(RetrofitClient.BASE_URL + item.photo).into(binding?.photo)
        binding?.title.text = item?.title
        binding?.content.text = item?.content
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
