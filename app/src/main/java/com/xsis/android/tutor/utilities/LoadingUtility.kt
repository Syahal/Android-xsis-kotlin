package com.xsis.android.tutor.utilities

import android.app.ProgressDialog
import android.content.Context
import com.xsis.android.tutor.R

fun Context.loadingAnimationAndText(text: String) : ProgressDialog {
    val loading = ProgressDialog(this, R.style.customLoadingStyle)

    loading.setProgressStyle((android.R.style.Widget_ProgressBar_Small))
    loading.setCancelable(false)
    loading.setCanceledOnTouchOutside(false)
    loading.setMessage(text)

    return loading
}

fun Context.loadingAnimation(text: String) : ProgressDialog {
    val loading = ProgressDialog(this, R.style.customLoadingStyle)

    loading.setProgressStyle((android.R.style.Widget_ProgressBar_Small))
    loading.setCancelable(false)
    loading.setCanceledOnTouchOutside(false)

    return loading
}