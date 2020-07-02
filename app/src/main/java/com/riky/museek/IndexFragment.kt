package com.riky.museek

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_index.view.*

class IndexFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_index, container, false)

        view.regButtonIndex.setOnClickListener {
            Log.d(IndexFragment::class.java.name, "Start Registration Fragment")
            fragmentManager!!.beginTransaction().replace(R.id.fragment, RegistrationFragment()).addToBackStack(null).commit()
        }

        view.logButtonIndex.setOnClickListener {
            Log.d(IndexFragment::class.java.name, "Start Login Fragment")
            fragmentManager!!.beginTransaction().replace(R.id.fragment, LoginFragment()).addToBackStack(null).commit()
        }

        return view
    }
}