package com.example.buildings.ui.Feeds.Partners

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.buildings.api.Api
import com.example.buildings.model.Partner
import com.example.buildings.model.PartnerResponse
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PartnersRepository: KoinComponent {
    var partners: MutableLiveData<List<Partner>> = MutableLiveData()
    var isPartnersLoading = MutableLiveData<Boolean>()

    val api: Api by inject()

    init {
        getPartner()
    }

    private fun logger(msg: String) {
        Log.d("MDIS", msg)
    }
    private fun getPartner(){
        logger("Partner Function called")
        isPartnersLoading.postValue(true)
        val call: Call<PartnerResponse> = api.partner
        call.enqueue(object : Callback<PartnerResponse>{
            override fun onFailure(call: Call<PartnerResponse>, t: Throwable) {
                logger("Partner Fail")
                isPartnersLoading.postValue(false)
            }

            override fun onResponse(call: Call<PartnerResponse>, response: Response<PartnerResponse>) {
                logger("Partner Response")
                if(response.isSuccessful){
                    Log.d("Sponsors", "Size : " + response.body()?.data?.size)
                    isPartnersLoading.postValue(false)
                    partners.postValue(response?.body()?.data)
                }
                else{
                    isPartnersLoading.postValue(false)
                }
            }
        })

    }
}