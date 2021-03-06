package com.riky.museek.classes

import android.app.AlertDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.storage.FirebaseStorage
import com.riky.museek.R
import com.riky.museek.activities.HomepageActivity
import com.riky.museek.fragments.AdDetailsInstrumentFragment
import com.riky.museek.fragments.ShowAdsInstrumentFragment
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.ad_card_show_ads_instrument.view.*
import kotlinx.android.synthetic.main.fragment_show_ads_instrument.view.*
import java.text.NumberFormat
import java.util.*

class AdItemShowAdsInstrument (private val ad: AdInstrument, val view: View, val alertDialog: AlertDialog, private val stopLoading: Boolean): Item<ViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.ad_card_show_ads_instrument
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {

        val formatter = NumberFormat.getCurrencyInstance()
        formatter.maximumFractionDigits = 2
        formatter.currency = Currency.getInstance("EUR")

        viewHolder.itemView.brandTextViewShowAdsInstr.text = ad.brand
        viewHolder.itemView.modelTextViewShowAdsInstr.text = ad.model
        viewHolder.itemView.categoryTextViewShowAdsInstr.text = DBManager.getCategoryById(ad.category)
        viewHolder.itemView.priceTextViewShowAdsInstr.text = formatter.format(ad.price)

        setListeners(viewHolder)

        val ref = FirebaseStorage.getInstance().getReference("/images/instrument_ads/")
        ref.child(ad.photoId).getBytes(4*1024*1024)
            .addOnSuccessListener { bytes ->
                val bitmap = BitmapFactory.decodeByteArray(bytes, 0 ,bytes.size)
                viewHolder.itemView.adPhotoShowAdsInstr.setImageBitmap(bitmap)
                viewHolder.itemView.adPhotoFrameShowAdsInstr.alpha = 1f
                if (stopLoading) {
                    alertDialog.dismiss()
                }
            }
            .addOnFailureListener {
                if (stopLoading) {
                    alertDialog.dismiss()
                }
            }
    }

    private fun setListeners(viewHolder: ViewHolder) {

        view.homeButtonShowAdsInstr.setOnClickListener {
            val intentHomepage = Intent(viewHolder.itemView.context, HomepageActivity::class.java)
            intentHomepage.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(viewHolder.itemView.context, intentHomepage, null)
        }

        viewHolder.itemView.detailsButtonShowAdsInstr.setOnClickListener {

            val adFragment = AdDetailsInstrumentFragment()
            val args = Bundle()
            args.putString("aid", ad.aid)
            adFragment.arguments = args

            val context = viewHolder.itemView.context as AppCompatActivity
            context.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, adFragment)
                .addToBackStack(ShowAdsInstrumentFragment::javaClass.name)
                .commit()
        }
    }
}