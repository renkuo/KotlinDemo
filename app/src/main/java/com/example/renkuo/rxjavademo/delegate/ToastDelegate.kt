package com.example.renkuo.rxjavademo.delegate

import android.app.Fragment
import android.content.Context
import android.widget.Toast

/**
 * Toast代理
 */

var activityToast: Toast? = null
var fragmentToast: Toast? = null


fun Context.toast(text: String?, duration: Int = Toast.LENGTH_SHORT){

    if (activityToast != null) {
        activityToast?.setText(text)
        activityToast?.duration = duration
    }else{
        activityToast = Toast.makeText(this, text, duration)
    }
    activityToast?.show()
}


fun Fragment.toast(text: String, duration: Int = Toast.LENGTH_SHORT){
    if (fragmentToast != null) {
        fragmentToast?.setText(text)
    }else{
        fragmentToast = Toast.makeText(this.activity, text, duration)
    }
    fragmentToast?.show()
}