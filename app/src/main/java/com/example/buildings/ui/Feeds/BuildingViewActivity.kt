package com.example.buildings.ui.Feeds

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.content.Intent
import android.database.Observable
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.Palette
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.buildings.App
import com.example.buildings.Const
import com.example.buildings.R
import com.example.buildings.adapters.GalleryAdapter
import com.example.buildings.adapters.NewsAdapter
import com.example.buildings.adapters.PartnersAdapter
import com.example.buildings.api.RetrofitClient
import com.example.buildings.databinding.ActivityViewBinding
import com.example.buildings.model.Building
import com.example.buildings.model.Event
import com.example.buildings.model.News
import com.example.buildings.model.Partner
import com.example.buildings.ui.Feeds.Partners.PartnersVM
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_view.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.File
import java.util.*

class BuildingViewActivity : AppCompatActivity(), View.OnClickListener {
        private lateinit var binding: ActivityViewBinding
        val vm: PartnersVM by viewModel()

        private var appBarExpanded: Boolean = true
        private var collapsedMenu: Menu? = null
        private lateinit var appBarLayout: AppBarLayout
        lateinit var itemm: Building

        lateinit var galleryAdapter: GalleryAdapter
        lateinit var partnersAdapter: PartnersAdapter
        lateinit var layoutManagerPartners: LinearLayoutManager
        lateinit var layoutManager: LinearLayoutManager

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view)
        Log.d("MYNEW", "On Create")

        if(intent == null)
            finish()

        itemm = intent.getSerializableExtra(Const.EVENT_EXTRA) as Building

        setSupportActionBar(anim_toolbar)
        if(supportActionBar != null)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        appBarLayout = binding?.appbar!!

        collapsing_toolbar.title = itemm?.name
        Picasso.get().load(RetrofitClient.BASE_URL + itemm.photoBackground).fit().into(binding?.header)
        Picasso.get().load(RetrofitClient.BASE_URL + itemm.organizationLogo).into(binding?.logo)
       // val file: String = File(Const.EVENT_EXTRA+item?.photoBackground).absolutePath

        val bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.roard)
        Palette.from(bitmap).generate { p0 ->
            val vibrantColor = p0!!.getVibrantColor(R.color.primary_500)
            binding?.collapsingToolbar!!.setContentScrimColor(vibrantColor)
            binding?.collapsingToolbar!!.setStatusBarScrimColor(R.color.black_trans80)
        }

       /* // Generate palette synchronously and return it
        fun createPaletteSync(bitmap: Bitmap): Palette = Palette.from(bitmap).generate()

        // Generate palette asynchronously and use it on a different
// thread using onGenerated()
        fun createPaletteAsync(bitmap: Bitmap) {
            Palette.from(bitmap).generate { palette ->

                // Use generated instance
            }
        }

        val vibrant = myPalette.vibrantSwatch
// In Kotlin, check for null before accessing properties on the vibrant swatch.
        val titleColor = vibrant?.titleTextColor
        fun setToolbarColor(bitmap: Bitmap) {
            // Generate the palette and get the vibrant swatch
            val vibrantSwatch = createPaletteSync(bitmap).vibrantSwatch

            // Set the toolbar background and text colors.
            // Fall back to default colors if the vibrant swatch is not available.
            with(findViewById<Toolbar>(R.id.toolbar)) {
                setBackgroundColor(vibrantSwatch?.rgb ?:
                ContextCompat.getColor(context, R.color.primary_500))
                setTitleTextColor(vibrantSwatch?.titleTextColor ?:
                ContextCompat.getColor(context, R.color.black_trans80))
            }
        }*/

        binding?.appbar!!.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            // Vertical offset == 0 indicates appBar is fully expanded.
            if (Math.abs(verticalOffset) > 200) {
                appBarExpanded = false
                invalidateOptionsMenu()
            } else {
                appBarExpanded = true
                invalidateOptionsMenu()
            }
        })
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        layoutManagerPartners = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        galleryAdapter = GalleryAdapter(this, itemm?.photoGallery, this)
        partnersAdapter = PartnersAdapter(this, ArrayList(), this)


        binding?.photoGallery!!.layoutManager = layoutManager
        binding?.photoGallery!!.adapter = galleryAdapter
        binding?.partners!!.layoutManager = layoutManagerPartners
        binding?.partners!!.adapter = partnersAdapter

        vm.partners.observe(this, object : Observer<List<Partner>> {
            override fun onChanged(t: List<Partner>?) {
                partnersAdapter.items = t!!
                partnersAdapter.notifyDataSetChanged()
            }
        })

        binding?.fab.setOnClickListener{
            Call(itemm?.phone)
        }

        /*if(item.photoBackground != null){
            val bitmap: Bitmap = BitmapFactory.decodeFile(file)
            Palette.from(bitmap).generate { p0 ->
                val vibrantColor = p0!!.getVibrantColor(R.color.primary_500)
                binding?.collapsingToolbar!!.setContentScrimColor(vibrantColor)
                binding?.collapsingToolbar!!.setStatusBarScrimColor(R.color.black_trans80)
            }
        }*/
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if(collapsedMenu != null && (!appBarExpanded || collapsedMenu!!.size() != 1)){
            collapsedMenu!!.add("Add")
                .setIcon(R.drawable.ic_phone_black_24dp)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        } else{
            //expanded
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_view, menu)
        collapsedMenu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            /*R.id.home -> {
                finish()
                return true
            }*/
        R.id.action_view -> return true
        }
        if(item.title == "Add"){
            Call(itemm.phone)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun Call(call: String){
         intent = Intent(Intent.ACTION_DIAL)
         intent.setData(Uri.parse("tel:" + call.trim()));
         startActivity(intent)
    }

    override fun onClick(v: View?) {

    }
}