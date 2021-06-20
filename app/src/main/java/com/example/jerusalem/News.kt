package com.example.jerusalem

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.jerusalem.adapter.NewsAdapter
import com.example.jerusalem.model.NewsModel
import kotlinx.android.synthetic.main.fragment_news.*


/**
 * A simple [Fragment] subclass.
 */
class News : Fragment(), NewsAdapter.onClick {
    val TAG = "tag"
    var list = ArrayList<NewsModel>()
    private var mQueue: RequestQueue? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_news, container, false)
        val pd = ProgressDialog(activity)
        pd.setMessage("انتظر قليلا...")
        pd.show()
        mQueue = Volley.newRequestQueue(activity);
        val url =
            "https://newsapi.org/v2/everything?q=%D8%A7%D9%84%D9%82%D8%AF%D8%B3&from=2021-06-16&to=2021-06-18&sortBy=popularity&apiKey=f81fc7ea05e3430daa56ba3e35daf7d8"
        val request = object : JsonObjectRequest(Request.Method.GET, url, null, { response ->

            Log.d("tag", response.toString())
            val jsonArray = response.getJSONArray("articles")
            for (i in 0 until jsonArray.length()) {
                val articles = jsonArray.getJSONObject(i)
                val title = articles.getString("title")
                val description = articles.getString("description")
                val urlToImage = articles.getString("urlToImage")
                val publishedAt = articles.getString("publishedAt")
                val content = articles.getString("content")
                    list.add(NewsModel(title, description, urlToImage, publishedAt, content))

                pd.dismiss()
            }
            rvNews.layoutManager = LinearLayoutManager(context)
            val newsAdapter = NewsAdapter(activity!!, list, this)
            rvNews.adapter = newsAdapter

        }, { error -> Log.d("tag", "onErrorResponse: " + error.message) }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }

        mQueue!!.add(request)
        return root
    }

    override fun onClickItem(position: Int) {
        val fragment = Details()
        val i = Bundle()
        i.putString("title", list[position].mtitleNews)
        i.putString("description", list[position].mdescNews)
        i.putString("image", list[position].mimageNews)
        i.putString("time", list[position].mpublishedAt)
        fragment.setArguments(i)
        activity!!.supportFragmentManager.beginTransaction().replace(
            R.id.mainContainer,
            fragment
        ).addToBackStack(null).commit()

    }

}
