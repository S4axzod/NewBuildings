package com.example.buildings.di

import com.example.buildings.App
import com.example.buildings.api.Api
import com.example.buildings.api.OkHttpProvider
import com.example.buildings.ui.Feeds.FeedsVM
import com.example.buildings.ui.Feeds.Partners.PartnersVM
import com.example.buildings.ui.News.NewsVM
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { App() }
    factory { OkHttpProvider(get()) }
    factory<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://newbuilding.uz/")
            .client(get<OkHttpProvider>().getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    viewModel { FeedsVM(get()) }
    viewModel { NewsVM(get()) }
    viewModel { PartnersVM(get()) }
    factory<Api> { get<Retrofit>().create(Api::class.java) }

}
