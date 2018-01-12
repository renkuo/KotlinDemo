package com.example.renkuo.rxjavademo

import android.os.Bundle
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.renkuo.rxjavademo.adapter.MySimpleAdapter
import com.example.renkuo.rxjavademo.delegate.toast
import com.example.renkuo.rxjavademo.http.GitHubClient
import com.example.renkuo.rxjavademo.http.ServiceGenerator
import com.example.renkuo.rxjavademo.logutil.QLog
import com.example.renkuo.rxjavademo.model.GitHubRepo
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)//添加toolbar
        createRecyleViewLayout()
        getRequest()
        QLog.e("${Thread.currentThread().id}")
        QLog.e("${Looper.myLooper() == Looper.getMainLooper()}")

    }

    //设置menu菜单
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    //设置menu item点击事件
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_settings -> toast("settings")
            R.id.action_about -> toast("about")

        }
        return super.onOptionsItemSelected(item)
    }


    override fun onClick(v: View) {

    }

    fun createRecyleViewLayout(){
        recyclerview.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recyclerview.adapter = MySimpleAdapter(this)
        recyclerview.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerview)
    }

    fun getRequest() {
        var client = ServiceGenerator.instance.createService(GitHubClient::class.java)
        val call = client.reposForUser("fs-opensource")
        call.enqueue(object : Callback<List<GitHubRepo>> {
            override fun onResponse(call: Call<List<GitHubRepo>>, response: Response<List<GitHubRepo>>) {
                // The network call was a success and we got a response
                // TODO: use the repository list and display it
                response.raw()
                QLog.e( response.body().toString())
                val gitHubRepos = response.body()
                //forEach遍历集合
                gitHubRepos?.forEach {
                    QLog.e("id=${it.id},name=${it.name}")
                }
                QLog.e("${Thread.currentThread().id}")
                QLog.e("${Looper.myLooper() == Looper.getMainLooper()}")
            }
            override fun onFailure(call: Call<List<GitHubRepo>>, t: Throwable) {
                // the network call was a failure
                if (call.isCanceled()) {
                    QLog.e( "request was cancelled");
                }
                else {
                    QLog.e( "other larger issue, i.e. no network connection?");
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}
