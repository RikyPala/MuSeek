package com.riky.museek.classes

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import com.riky.museek.R
import kotlinx.android.synthetic.main.loading_popup_blue.*

class AlertDialogInflater {

    companion object {

        const val BLUE = 0
        const val RED = 1
        const val GREY = 2

        fun inflateLoadingDialog (context: Context, color: Int) : AlertDialog {

            val animation = RotateAnimation(
                0.0f,
                360.0f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
            animation.interpolator = LinearInterpolator()
            animation.repeatCount = Animation.INFINITE
            animation.duration = 700

            val dialogView =
                when (color) {
                    BLUE -> LayoutInflater.from(context).inflate(R.layout.loading_popup_blue, null)
                    GREY -> LayoutInflater.from(context).inflate(R.layout.loading_popup_grey, null)
                    RED -> LayoutInflater.from(context).inflate(R.layout.loading_popup_red, null)
                    else -> LayoutInflater.from(context).inflate(R.layout.loading_popup_blue, null)
                }

            val mBuilder = AlertDialog.Builder(context).setView(dialogView)
            val alertDialog = mBuilder.show()

            alertDialog.setCanceledOnTouchOutside(false)
            alertDialog.setCancelable(false)
            alertDialog.loadingImageView.startAnimation(animation)

            return alertDialog
        }

        fun inflatePasswordDialog (context: Context, color: Int) : AlertDialog {

            val dialogView =
                when (color) {
                    //BLUE -> LayoutInflater.from(context!!).inflate(R.layout.password_popup_blue, null)
                    GREY -> LayoutInflater.from(context).inflate(R.layout.password_popup_grey, null)
                    //RED -> LayoutInflater.from(context!!).inflate(R.layout.password_popup_red, null)
                    else -> LayoutInflater.from(context).inflate(R.layout.password_popup_grey, null)
                }

            val mBuilder = AlertDialog.Builder(context).setView(dialogView)

            return mBuilder.show()
        }

        fun inflateConfirmDeleteDialog (context: Context, color: Int) : AlertDialog {

            val dialogView =
                when (color) {
                    BLUE -> LayoutInflater.from(context).inflate(R.layout.confirm_delete_popup_blue, null)
                    //GREY -> LayoutInflater.from(context).inflate(R.layout.confirm_delete_popup_grey, null)
                    RED -> LayoutInflater.from(context).inflate(R.layout.confirm_delete_popup_red, null)
                    else -> LayoutInflater.from(context).inflate(R.layout.confirm_delete_popup_blue, null)
                }
            val mBuilder = AlertDialog.Builder(context).setView(dialogView)
            return mBuilder.show()
        }

        fun inflateConfirmPurchaseDialog (context: Context, color: Int) : AlertDialog {

            val dialogView =
                when (color) {
                    BLUE -> LayoutInflater.from(context).inflate(R.layout.confirm_purchase_popup_blue, null)
                    //GREY -> LayoutInflater.from(context).inflate(R.layout.confirm_purchase_popup_grey, null)
                    //RED -> LayoutInflater.from(context).inflate(R.layout.confirm_purchase_popup_red, null)
                    else -> LayoutInflater.from(context).inflate(R.layout.confirm_purchase_popup_blue, null)
                }
            val mBuilder = AlertDialog.Builder(context).setView(dialogView)
            return mBuilder.show()
        }

        fun inflateConfirmNotifyDialog (context: Context, color: Int) : AlertDialog {

            val dialogView =
                when (color) {
                    BLUE -> LayoutInflater.from(context).inflate(R.layout.confirm_notify_popup_blue, null)
                    //GREY -> LayoutInflater.from(context).inflate(R.layout.confirm_notify_popup_grey, null)
                    //RED -> LayoutInflater.from(context).inflate(R.layout.confirm_notify_popup_red, null)
                    else -> LayoutInflater.from(context).inflate(R.layout.confirm_notify_popup_blue, null)
                }
            val mBuilder = AlertDialog.Builder(context).setView(dialogView)
            return mBuilder.show()
        }

        fun inflateReviewDialog (context: Context, color: Int) : AlertDialog {

            val dialogView =
                when (color) {
                    BLUE -> LayoutInflater.from(context).inflate(R.layout.review_popup_blue, null)
                    //GREY -> LayoutInflater.from(context!!).inflate(R.layout.review_popup_grey, null)
                    //RED -> LayoutInflater.from(context!!).inflate(R.layout.review_popup_red, null)
                    else -> LayoutInflater.from(context).inflate(R.layout.review_popup_blue, null)
                }

            val mBuilder = AlertDialog.Builder(context).setView(dialogView)
            return mBuilder.show()
        }

        fun inflateChooseBandAdDialog (context: Context, color: Int) : AlertDialog {

            val dialogView =
                when (color) {
                    //BLUE -> LayoutInflater.from(context).inflate(R.layout.choose_band_ad_notify_blue, null)
                    //GREY -> LayoutInflater.from(context).inflate(R.layout.choose_band_ad_notify_grey, null)
                    RED -> LayoutInflater.from(context).inflate(R.layout.choose_band_ad_popup_red, null)
                    else -> LayoutInflater.from(context).inflate(R.layout.choose_band_ad_popup_red, null)
                }
            val mBuilder = AlertDialog.Builder(context).setView(dialogView)
            return mBuilder.show()
        }

        fun inflateSearchMemberDialog (context: Context, color: Int) : AlertDialog {

            val dialogView =
                when (color) {
                    //BLUE -> LayoutInflater.from(context).inflate(R.layout.search_member_popup_blue, null)
                    //GREY -> LayoutInflater.from(context).inflate(R.layout.search_member_popup_grey, null)
                    RED -> LayoutInflater.from(context).inflate(R.layout.search_member_popup_red, null)
                    else -> LayoutInflater.from(context).inflate(R.layout.search_member_popup_red, null)
                }
            val mBuilder = AlertDialog.Builder(context).setView(dialogView)
            return mBuilder.show()
        }

        fun inflateSearchBandDialog (context: Context, color: Int) : AlertDialog {

            val dialogView =
                when (color) {
                    //BLUE -> LayoutInflater.from(context).inflate(R.layout.search_band_popup_blue, null)
                    //GREY -> LayoutInflater.from(context).inflate(R.layout.search_band_popup_grey, null)
                    RED -> LayoutInflater.from(context).inflate(R.layout.search_band_popup_red, null)
                    else -> LayoutInflater.from(context).inflate(R.layout.search_band_popup_red, null)
                }
            val mBuilder = AlertDialog.Builder(context).setView(dialogView)
            return mBuilder.show()
        }

    }
}