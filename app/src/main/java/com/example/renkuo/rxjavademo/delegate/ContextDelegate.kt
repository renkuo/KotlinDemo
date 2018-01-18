package com.example.renkuo.rxjavademo.delegate

import android.content.Context
import android.widget.Toast

/**
 * Context扩展
 * Created by renkuo on 2018/1/18.
 */


/**
 * toast方法(可在Activity中直接调用)
 */
var activityToast: Toast? = null
fun Context.toast(text: String?, duration: Int = Toast.LENGTH_SHORT){

    if (activityToast != null) {
        activityToast?.setText(text)
        activityToast?.duration = duration
    }else{
        activityToast = Toast.makeText(this, text, duration)
    }
    activityToast?.show()
}