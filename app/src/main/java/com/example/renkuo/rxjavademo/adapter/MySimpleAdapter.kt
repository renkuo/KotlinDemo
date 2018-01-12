package com.example.renkuo.rxjavademo.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.renkuo.rxjavademo.R
import kotlinx.android.synthetic.main.layout_item.view.*



/**
 * Created by renkuo on 2017/12/15.
 */

class MySimpleAdapter(context: Context) : RecyclerView.Adapter<MySimpleAdapter.MyViewHolder>() {

    val mContext: Context
    val imageUrls = listOf<String>(
            "http://images.missyuan.com/attachments/day_120818/20120818_fa549d6d62bc71b6d64c48K000KnnG00.png",
            "http://img5.niutuku.com/phone/1301/4728/4728-niutuku.com-205300.png",
            "http://img.zcool.cn/community/01640f570ca52332f8751b3f28abfc.png"
    )

    init {

        this.mContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        val viewHolder = MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_item, parent, false))
        return viewHolder
    }

    override fun getItemCount(): Int {
        return imageUrls.size
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        holder?.tv?.text = "11"
        val url = imageUrls.get(position)
        Glide.with(mContext)
                .load(url)
                .into(holder?.iv)
    }


    inner class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        val tv: TextView
        val iv: ImageView

        init {
            tv = itemView?.textView!!
            iv = itemView?.imageView!!
        }

    }

}