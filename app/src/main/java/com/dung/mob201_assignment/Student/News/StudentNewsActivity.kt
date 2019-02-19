package com.dung.mob201_assignment.Student.News

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.AdapterView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.dung.mob201_assignment.Adapter.NewsAdapter
import com.dung.mob201_assignment.BaseActivity
import com.dung.mob201_assignment.Model.News
import com.dung.mob201_assignment.R
import com.dung.mob201_assignment.XmlDomParser
import kotlinx.android.synthetic.main.activity_student_news.*
import kotlinx.android.synthetic.main.dialog_news.view.*
import org.w3c.dom.Document
import org.w3c.dom.Element

class StudentNewsActivity : BaseActivity() {

    var list: ArrayList<News>? = null
    var adapter: NewsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_news)

        this.listViewAddition()
        this.listViewOnClickView()

    }

    fun listViewOnClickView(){
        lvNews.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            var news = list!!.get(position)

            var alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
            alertDialog.setTitle(resources.getString(R.string.titleWebViewDialog))
            alertDialog.setIcon(R.drawable.img_news)
            alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

            var view: View = layoutInflater.inflate(R.layout.dialog_news,null)
            var wvNews: WebView = view.wvNews
            wvNews.loadUrl(news.link)
            wvNews.webViewClient = WebViewClient()

            alertDialog.setView(view)
            var dialog = alertDialog.create()
            dialog.show()
        }
    }

    fun listViewAddition(){
        var requestQueue = Volley.newRequestQueue(this)
        var url = "https://vnexpress.net/rss/giao-duc.rss"
        list = ArrayList()
        adapter = NewsAdapter(this,list!!)
        lvNews.adapter = adapter

        var stringRequest = StringRequest(Request.Method.GET, url,
            object : Response.Listener<String>{
                override fun onResponse(response: String?) {
                    var xmlDomParser = XmlDomParser()
                    var document: Document = xmlDomParser.getDocument(response!!)
                    var nodeList = document.getElementsByTagName("item")
                    for (i in 0..nodeList.length - 1){
                        var element: Element = nodeList.item(i) as Element
                        var title = xmlDomParser.getValue(element, "title")
                        var link = xmlDomParser.getValue(element,"link")
                        list!!.add(News(title,link))
                    }
                    adapter!!.notifyDataSetChanged()
                }

            }, object : Response.ErrorListener{
                override fun onErrorResponse(error: VolleyError?) {
                    showMessage("Lỗi: "+error.toString())
                }
            })

        requestQueue.add(stringRequest)
    }
}
