package com.example.newsapi_tieasnura

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class NewsAdapter (private val listNews: ArrayList<NewsModel>) :
    RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {
    class MyViewHolder (itemView: View):RecyclerView.ViewHolder(itemView) {
        var newsimage: ImageView = itemView.findViewById(R.id.news_image)
        var title : TextView = itemView.findViewById(R.id.news_title)
        var source : TextView = itemView.findViewById(R.id.news_source)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view: View =
           LayoutInflater.from(parent.context).inflate(R.layout.news_card,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val context: Context = holder.itemView.context

        val p = listNews [position]
        holder.title.text = p.title
        holder.title.setOnClickListener(View.OnClickListener { v ->
            val intent = Intent(context, NewsPage::class.java)
            intent.putExtra("url",p.url)

            context.startActivity(intent)
        })
        Picasso.get().load(p.urlToImage).into(holder.newsimage)
        holder.source.text = p.source

    }

    override fun getItemCount(): Int {
        return  listNews.size
    }


}
