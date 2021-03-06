package com.riky.museek.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.riky.museek.R
import com.riky.museek.fragments.BandFragment

class BandActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_band)

        supportFragmentManager.beginTransaction().replace(R.id.fragment, BandFragment()).commit()
    }
}