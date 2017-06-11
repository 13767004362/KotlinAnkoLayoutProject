package com.xingen.ankolayout.movielist

import android.app.ProgressDialog
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.xingen.ankolayout.R
import com.xingen.ankolayout.utils.ViewID
import com.xingen.example.data.MovieList
import com.xingen.example.movielist.MovieListConstract
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI

/**
 * Created by ${新根} on 2017/6/11.
 * blog ：http://blog.csdn.net/hexingen
 */
class MovieListFragment : Fragment(), MovieListConstract.View {
    lateinit var presenters: MovieListConstract.Presenter
    lateinit var dialog: ProgressDialog
    lateinit var recyclerView: RecyclerView
   lateinit var rootView :View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       rootView = UI {
            verticalLayout {
                lparams(width= matchParent,height = matchParent)//设置布局的宽高
                textView(R.string.title) {//设置tile
                    textSize = 18f//字体大小
                    paint.isFakeBoldText = true//设置粗体
                    gravity = Gravity.CENTER_HORIZONTAL//控件中字体水平居中
                    verticalPadding = dip(10) //控件的上下Padding值
                    textColor=Color.WHITE//字体颜色
                    backgroundResource = R.color.colorPrimaryDark//控件的背景
                }.lparams(width = matchParent, height = wrapContent)//控件的宽高
                recyclerView {//设置滚动视图RecyclerView
                    id = ViewID.RECYCLERVIEW_ID//控件的id
                }.lparams(width = matchParent, height = matchParent)//控件的宽高
            }
        }.view
        recyclerView = rootView.findViewById(ViewID.RECYCLERVIEW_ID) as RecyclerView // 根据id获取指定控件
        return rootView
    }

    override fun onResume() {
        super.onResume()
        presenters.subscribe()
    }

    override fun onPause() {
        super.onPause()
        presenters.unsubscribe()
    }

    override fun setPresenter(presenter: MovieListConstract.Presenter) {
        this.presenters = presenter
    }

    override fun showToast(msg: String) {
        Toast.makeText(activity.applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    /**
     * 加载数据
     */
    override fun loadMovie(list: List<MovieList.Movie>) {
        recyclerView .layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = MovieListAdapter(activity, list)
    }

    override fun showDialog() {
        dialog = ProgressDialog(activity)
        dialog.show()
    }

    override fun cancleDialog() {
        if (dialog != null && dialog.isShowing) {
            dialog.cancel()
        }
    }

    companion object {
        val instance = MovieListFragment()
        val TAG = MovieListFragment::class.java.simpleName
    }
}
