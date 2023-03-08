package com.example.newsapi_tieasnura

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: NewsAdapter
    private lateinit var recyclerView: RecyclerView

    private val newslist: ArrayList <NewsModel> = ArrayList<NewsModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = NewsAdapter(newslist)
        recyclerView = findViewById(R.id.rv_news)

        fetchData()
    }

    private fun fetchData() {
        //volly library
        var apiKey = "bc5e8207c8d347f7b15cf11656b49098"
        var country = "us"
        val queue = Volley.newRequestQueue(this)
        val url = String.format("https://newsapi.org/v2/top-headlines?apiKey=$apiKey&country=$country",apiKey,country)

        //making a request
        val jsonObjectRequest = object: JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {

                val newsJsonArray = it.getJSONArray("articles")
                for (i in 0 until newsJsonArray.length()) {
                    val news: JSONObject = newsJsonArray.getJSONObject(i)
                    val title = news.getString("title")
                    val source = news.getJSONObject("source")
                    val source_name = source.getString("name")
                    val imageurl = news.getString("urlToImage")

                    val newsview = NewsModel(title,source_name,imageurl)

                    newslist.add(newsview)

                    adapter.notifyDataSetChanged()
                }
                Log.e("cek",newsJsonArray.toString())

            },
            Response.ErrorListener {  Log.e("ERR", "ERROR CONNECTING")
            }

        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }

        queue.add(jsonObjectRequest)

        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
    }
}