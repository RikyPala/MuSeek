package com.riky.museek.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.riky.museek.R
import com.riky.museek.activities.HomepageActivity
import com.riky.museek.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_my_profile.view.*

class MyProfileFragment : Fragment()  {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_my_profile, container, false)

        view.homeButtonMyProfile.setOnClickListener {
            val intentHomepage = Intent(activity, HomepageActivity::class.java)
            intentHomepage.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intentHomepage)
        }

        view.editProfileButtonMyProfile.setOnClickListener {
            fragmentManager!!.beginTransaction()
                .replace(R.id.fragment, EditProfileFragment())
                .addToBackStack(this.javaClass.name)
                .commit()
        }

        view.editEmailButtonMyProfile.setOnClickListener {
            fragmentManager!!.beginTransaction()
                .replace(R.id.fragment, EditEmailFragment())
                .addToBackStack(this.javaClass.name)
                .commit()
        }

        view.editPasswordButtonMyProfile.setOnClickListener {
            fragmentManager!!.beginTransaction()
                .replace(R.id.fragment, EditPasswordFragment())
                .addToBackStack(this.javaClass.name)
                .commit()
        }

        view.logoutButtonMyProfile.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intentMain = Intent(activity, MainActivity::class.java)
            intentMain.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intentMain)
        }

        return view
    }
}