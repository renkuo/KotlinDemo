package com.example.renkuo.rxjavademo.delegate

import android.app.Fragment
import android.widget.Toast


/**
 * toast方法(可在Fragment中直接调用)
 */
var fragmentToast: Toast? = null
fun Fragment.toast(text: String, duration: Int = Toast.LENGTH_SHORT){
    if (fragmentToast != null) {
        fragmentToast?.setText(text)
    }else{
        fragmentToast = Toast.makeText(this.activity, text, duration)
    }
    fragmentToast?.show()
}