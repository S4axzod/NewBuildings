package com.example.buildings

import android.app.Application
import org.koin.android.ext.android.startKoin
import android.graphics.Typeface
import com.example.buildings.di.appModule
import java.util.prefs.Preferences


class App : Application() {

//    var museo300: Typeface? = null
//    var museo500: Typeface? = null
//    var museo700: Typeface? = null
    var museo900: Typeface? = null
//    var kaz_bold: Typeface? = null
//    var kaz_regular: Typeface? = null
//    var sfns_regular: Typeface? = null
//    var kaz_book_italic: Typeface? = null


    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }

//    fun getKaz_book_italic(): Typeface {
//        if (kaz_book_italic == null) {
//            kaz_book_italic = Typeface.createFromAsset(this.getAssets(), "fonts/kazimir_text_book_italic.ttf")
//        }
//        return kaz_book_italic!!
//    }
//
//    fun getKaz_bold(): Typeface {
//        if (kaz_bold == null) {
//            kaz_bold = Typeface.createFromAsset(this.getAssets(), "fonts/kazimir_text_bold.ttf")
//        }
//        return kaz_bold!!
//    }
//
//    fun getKaz_regular(): Typeface {
//        if (kaz_regular == null) {
//            kaz_regular = Typeface.createFromAsset(this.getAssets(), "fonts/kazimir_text_regular.ttf")
//        }
//        return kaz_regular!!
//    }
//
//    fun getMuseo300(): Typeface {
//        if (museo300 == null) {
//            museo300 = Typeface.createFromAsset(this.getAssets(), "fonts/museo_sans_cyrl_300.ttf")
//        }
//        return museo300!!
//    }
//
//    fun getMuseo500(): Typeface {
//        if (museo500 == null) {
//            museo500 = Typeface.createFromAsset(this.getAssets(), "fonts/museo_sans_cyrl_500.ttf")
//        }
//        return museo500!!
//    }
//
//    fun getMuseo700(): Typeface {
//        if (museo700 == null) {
//            museo700 = Typeface.createFromAsset(this.getAssets(), "fonts/museo_sans_cyrl_700.ttf")
//        }
//        return museo700!!
//    }

    fun font900(): Typeface {
        if (museo900 == null) {
            museo900 = Typeface.createFromAsset(this.getAssets(), "fonts/museo_sans_cyrl_900.ttf")
        }
        return museo900!!
    }

}