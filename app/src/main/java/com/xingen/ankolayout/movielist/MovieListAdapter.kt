package com.xingen.ankolayout.movielist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.xingen.ankolayout.utils.ViewID
import com.xingen.ankolayout.utils.glide.GlideUtils
import com.xingen.example.data.MovieList
import org.jetbrains.anko.*

/**
 * Created by ${新根} on 2017/6/11.
 * blog ：http://blog.csdn.net/hexingen
 */
class MovieListAdapter(val context: Context,var list: List<MovieList.Movie>) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    /**
     * 重复使用的Context添加多个view.
     */
    private val ankoContext = AnkoContext.createReusable(context, this)
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(MovieListAdapterUI().createView(ankoContext))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        GlideUtils.loadUrlImage(context,list[position].images.large,holder.imageView)
        holder.title_Tv.text=list[position].title
    }
    override fun getItemCount() = list.size
    /**
     * 自定义ViewHolder
     */
    inner class ViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
         val  imageView=rootView.findViewById(ViewID.IMAGEVIEW_ID) as ImageView
         val  title_Tv=rootView.findViewById(ViewID.TITLE_ID) as TextView
    }
}

/**
 * 使用AnkoComponent来构建item的UI
 */
class MovieListAdapterUI : AnkoComponent<MovieListAdapter> {
    override fun createView(ui: AnkoContext<MovieListAdapter>) = with(ui) {
        linearLayout {
            padding = dip(10)//设置padding
            imageView {
                id = ViewID.IMAGEVIEW_ID//设置id
                scaleType = ImageView.ScaleType.CENTER_CROP//图片中心裁剪
            }.lparams(width = dip(60), height = dip(60))//设置图片的宽高
            textView {
                id = ViewID.TITLE_ID//设置id
                textSize = 14f//字体大小
                paint.isFakeBoldText = true//粗体
            }.lparams {
                leftMargin = dip(10)//左边偏移量
                gravity = Gravity.CENTER_VERTICAL//竖直居中
            }
        }
    }
}

