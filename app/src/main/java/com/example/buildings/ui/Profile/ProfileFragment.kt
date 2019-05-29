package com.example.buildings.ui.Profile

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.buildings.R


class ProfileFragment : Fragment(){
    private var mView: View? = null
    private lateinit var fab: Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_profile, container, false)

        return mView
    }
}