package com.example.marvel.superheros.commons.extentions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

/**
 * @author kiran
 */

fun getScreenWidth(context: Context): Int {
    val metrics = getDisplayMetrics(context)
    return metrics.widthPixels
}
@SuppressLint("WrongConstant")
fun getDisplayMetrics(context: Context): DisplayMetrics {
    return if (context is Activity) {
        val metrics = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(metrics)
        metrics
    } else {
        val wm = context.getSystemService("window") as WindowManager
        val metrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(metrics)
        metrics
    }
}
@SuppressLint("WrongConstant")
fun closeSoftKeyboard(activity: Activity) {
    try {
        val inputManager = activity.getSystemService("input_method") as InputMethodManager
        inputManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 2)
    } catch (var3: Exception) {
    }
}