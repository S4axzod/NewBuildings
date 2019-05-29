package com.example.buildings.model

import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.Expose
import java.io.Serializable

data class Building (
    @Expose val id: Int,
    @Expose val organization_id: Int,
    @Expose val phone: String,
    @Expose val countFlats: Int,
    @Expose val cityName: String,
    @Expose val name: String,
    @Expose val address: String,
    @Expose val date_build: String,
    @Expose val map_location: String,
   // @Expose val price: String,
    //@Expose val price_end: String,
    @Expose val priceType: String,
    @Expose val currencyName: String,
    @Expose val kvm: Double,
    @Expose val kvm_till: Double,
    @Expose val content: String,
    @Expose val content_mini: String,
    @Expose val premium: Int,
    @Expose val photo: String,
    @Expose val organizationLogo: String,
    @Expose val photoBackground: String,
    @Expose val photoGallery : Array<String>,
    @Expose val views: Int
   // @Expose val videoFile: String
) : Serializable