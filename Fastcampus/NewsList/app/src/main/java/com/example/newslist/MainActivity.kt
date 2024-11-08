package com.example.newslist

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newslist.databinding.ActivityMainBinding
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    companion object {
        private val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding

    private lateinit var newsAdapter : NewsAdapter

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://news.google.com/")
        .addConverterFactory(
            TikXmlConverterFactory.create(
                TikXml.Builder()  //TikXML 객체를 생성
                    .exceptionOnUnreadXml(false)  //XML에서 읽히지 않는 요소가 있을때 예외 발생시키지 않도록 설정
                    .build()
            )
        ).build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        newsAdapter = NewsAdapter()
        binding.newsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }
        val newsService = retrofit.create(NewsService::class.java)
        newsService.mainFeed().enqueue(object : Callback<RSSNews> {
            override fun onResponse(call: Call<RSSNews>, response: Response<RSSNews>) {
                Log.d(TAG, "${response.body()?.rssChannel?.items}")

                val list = response.body()?.rssChannel?.items.orEmpty().transform()
                newsAdapter.submitList(list)
                list.forEach {
                    Thread {
                        val item = list.first()

                        val jsoup = Jsoup.connect(item.link).get()
                        val elements = jsoup.select("meta[property^=og:]")
                        val ogImageNode = elements.find { node->
                            node.attr("property") == "og:image"
                        }
                        it.imageURL = ogImageNode?.attr("content")
                    }.start()
                }
            }

            override fun onFailure(call: Call<RSSNews>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

}