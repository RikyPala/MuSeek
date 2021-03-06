package com.riky.museek.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.riky.museek.R
import com.riky.museek.activities.HomepageActivity
import com.riky.museek.classes.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_sold_ads_instrument.*
import kotlinx.android.synthetic.main.fragment_sold_ads_instrument.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SoldAdsInstrumentFragment : Fragment() {

    private val adapter = GroupAdapter<ViewHolder>()
    private val adsMap = HashMap<String, PurchasedAdInstrument>()
    private var viewer: View? = null
    private val STOP_LOADING = 3
    private var alertDialog : AlertDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewer = inflater.inflate(R.layout.fragment_sold_ads_instrument, container, false)

        viewer!!.homeButtonSoldAdsInstr.setOnClickListener {
            val intentHomepage = Intent(activity, HomepageActivity::class.java)
            intentHomepage.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intentHomepage)
        }

        if (context != null) DBManager.verifyLoggedUser(context!!)

        alertDialog = AlertDialogInflater.inflateLoadingDialog(context!!, AlertDialogInflater.BLUE)

        viewer!!.recyclerViewSoldAdsInstr.adapter = adapter

        fetchSoldAdsFromDatabase()

        GlobalScope.launch {
            delay(3000L)
            alertDialog!!.dismiss()
        }

        return viewer!!
    }

    private fun refreshRecyclerView(){
        adapter.clear()
        if (adsMap.isEmpty()){
            alertDialog!!.dismiss()
            noResultsTextViewSoldAdsInstr.visibility = View.VISIBLE
            return
        }
        val stop = if (adsMap.size>=STOP_LOADING) STOP_LOADING else adsMap.size
        var i = 1
        adsMap.values.forEach {
            adapter.add(AdItemSoldAdsInstrument(it, viewer!!, alertDialog!!, i == stop))
            i++
        }
    }

    private fun fetchSoldAdsFromDatabase() {

        if (context != null) DBManager.verifyLoggedUser(context!!)

        val ref = FirebaseDatabase.getInstance().getReference("/instrument_purchased_ads/")

        val uid = FirebaseAuth.getInstance().uid ?: ""

        var ad: PurchasedAdInstrument

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                adapter.clear()
                if (dataSnapshot.exists()) {
                    for (ads in dataSnapshot.children) {
                        if (ads.child("selleruid").value == uid) {
                            ad = PurchasedAdInstrument(
                                ads.key as String,
                                ads.child("brand").value as String,
                                ads.child("model").value as String,
                                ads.child("price").value.toString().toDouble(),
                                ads.child("category").value.toString().toInt(),
                                ads.child("condition").value.toString().toInt(),
                                ads.child("photoId").value as String,
                                ads.child("buyeruid").value as String,
                                uid,
                                ads.child("date").value as String)
                            adsMap[ads.value.toString()] = ad
                        }
                    }
                    refreshRecyclerView()
                }
                else {
                    alertDialog!!.dismiss()
                    noResultsTextViewSoldAdsInstr.visibility = View.VISIBLE
                }
                ref.removeEventListener(this)
            }
            override fun onCancelled(databaseError: DatabaseError) {
                alertDialog!!.dismiss()
                noResultsTextViewSoldAdsInstr.visibility = View.VISIBLE
                Log.d(PurchasedAdsInstrumentFragment::class.java.name, "ERROR on Database: ${databaseError.message}")
                ref.removeEventListener(this)
            }
        })
    }
}