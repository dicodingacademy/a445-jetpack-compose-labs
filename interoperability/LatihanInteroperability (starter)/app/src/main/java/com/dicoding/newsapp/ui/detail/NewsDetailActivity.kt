package com.dicoding.newsapp.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat
import com.dicoding.newsapp.R
import com.dicoding.newsapp.data.local.entity.NewsEntity
import com.dicoding.newsapp.databinding.ActivityNewsDetailBinding
import com.dicoding.newsapp.ui.ViewModelFactory

class NewsDetailActivity : AppCompatActivity() {

    private lateinit var newsDetail: NewsEntity
    private lateinit var binding: ActivityNewsDetailBinding
    private val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
    private val viewModel: NewsDetailViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newsDetail = IntentCompat.getParcelableExtra(intent, NEWS_DATA, NewsEntity::class.java) as NewsEntity

        binding.topAppBar.title = newsDetail.title

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_bookmark -> {
                    viewModel.changeBookmark(newsDetail)
                    true
                }
                else -> false
            }
        }

        viewModel.bookmarkStatus.observe(this) { status ->
            setBookmarkState(status)
        }

        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl(newsDetail.url.toString())

        viewModel.setNewsData(newsDetail)
    }

    private fun setBookmarkState(state: Boolean) {
        val menuItem = binding.topAppBar.menu.findItem(R.id.action_bookmark)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmarked_white)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark_white)
        }
    }

    companion object {
        const val NEWS_DATA = "data"
    }
}