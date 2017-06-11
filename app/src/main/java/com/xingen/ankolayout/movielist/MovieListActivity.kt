package com.xingen.ankolayout.movielist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.xingen.ankolayout.utils.ViewID
import com.xingen.example.movielist.MovieListPresenter
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.relativeLayout
import org.jetbrains.anko.setContentView

/**
 * Created by ${新根} on 2017/6/10.
 * blog ：http://blog.csdn.net/hexingen
 */
class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityUI().setContentView(this)//Anko Component接口的扩展方法setContentView（）

        var view=MovieListFragment.instance
        MovieListPresenter(view)
        supportFragmentManager.beginTransaction().add(ViewID.CONTENT_LAYOUT, view, MovieListFragment.TAG).commit()
    }
}

/**
 * 使用AnkoComponent
 */
class ActivityUI : AnkoComponent<MovieListActivity> {
    override fun createView(ui: AnkoContext<MovieListActivity>) = with(ui) {
        relativeLayout{//RelativeLayout作为根布局
           id= ViewID.CONTENT_LAYOUT//设置id，方便添加Fragment
        }
    }
}