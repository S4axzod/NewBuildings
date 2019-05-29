package com.example.buildings.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.buildings.R
import com.example.buildings.api.Api
import com.example.buildings.model.Building
import com.example.buildings.ui.Feeds.FeedsVM
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.math.log

class BookingFragment : Fragment(), OnMapReadyCallback{
    //val vm: FeedsVM by viewModel()

    var items: List<Building> = ArrayList()
    private val options:MarkerOptions = MarkerOptions()
    var latLng: ArrayList<LatLng> = ArrayList()
    internal lateinit var view: View
    private lateinit var googleMap: GoogleMap
    private lateinit var mapView:MapView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.fragment_booking, container, false)

        /*vm.building.observe(this, object : Observer<List<Building>> {
            override fun onChanged(t: List<Building>?) {

            }
        })*/

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mapView = view.findViewById(R.id.map)
        if(mapView != null){
            mapView.onCreate(null)
            mapView.onResume()
            mapView.getMapAsync(this)
        }
    }


    override fun onMapReady(p0: GoogleMap?) {
        googleMap = p0!!
        p0.mapType = GoogleMap.MAP_TYPE_NORMAL
        for (point in latLng)
        {
            options.position(point)
            options.title("someTitle")
            options.snippet("someDesc")
            googleMap.addMarker(options)
        }
        val Tashkent:LatLng = LatLng(41.311081,69.240562)
        val NewYork:LatLng = LatLng(40.689247, -74.044502)
        p0.addMarker(MarkerOptions().position(NewYork)
        .title("Statue of Liberty").snippet("I hope I'll go there some day"))
        var cameraPosition:CameraPosition = CameraPosition.builder().target(NewYork).
            zoom(16F).bearing(0F).tilt(45F).build()
        p0.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    fun locationBuildings(buildings: List<Building>?): List<String>{
        var res = ArrayList<String>()

        buildings!!.forEach {
            res.add(it.map_location)
           // latLng.add(LatLng(res[0].toDouble(), res[1].toDouble()))
        }

        return res

    }
}