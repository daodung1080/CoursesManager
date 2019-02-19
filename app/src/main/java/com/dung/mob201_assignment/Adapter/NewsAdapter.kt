package com.dung.mob201_assignment.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.dung.mob201_assignment.Model.News
import com.dung.mob201_assignment.R
import kotlinx.android.synthetic.main.list_item_news.view.*

class NewsAdapter(var context: Context, var arrayList: ArrayList<News>): BaseAdapter() {

    class ViewHolder(view: View){
        var txtNewsTitle: TextView
        var txtNewsLink: TextView

        init {
            txtNewsTitle = view.txtNewsTitle
            txtNewsLink = view.txtNewsLink
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder: ViewHolder
        var view: View?

        if(convertView == null){
            var layoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.list_item_news,null)
            holder = ViewHolder(view)
            view.tag = holder
        }
        else{
            view = convertView
            holder = view.tag as ViewHolder
        }

        var news: News = getItem(position) as News
        holder.txtNewsTitle.text = news.title
        holder.txtNewsLink.text= news.link

        return view as View
    }

    override fun getItem(position: Int): Any {
        return arrayList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arrayList.size
    }
}