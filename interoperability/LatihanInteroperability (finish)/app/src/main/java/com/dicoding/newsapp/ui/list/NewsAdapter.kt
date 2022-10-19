package com.dicoding.newsapp.ui.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.compose.AsyncImage
import com.dicoding.newsapp.data.local.entity.NewsEntity
import com.dicoding.newsapp.databinding.ItemNewsBinding
import com.dicoding.newsapp.ui.list.NewsAdapter.MyViewHolder

class NewsAdapter(private val onItemClick: (NewsEntity) -> Unit) :
    ListAdapter<NewsEntity, MyViewHolder>(
        DIFF_CALLBACK
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val news = getItem(position)
        holder.bind(news)
    }

    class MyViewHolder(
        private val binding: ItemNewsBinding,
        val onItemClick: (NewsEntity) -> Unit
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(news: NewsEntity) {
            binding.composeView.setContent {
                MaterialTheme {
                    NewsItem(
                        news.urlToImage,
                        news.title,
                        news.publishedAt,
                        onItemClick = { onItemClick(news) }
                    )
                }
            }
//            binding.tvItemTitle.text = news.title
//            binding.tvItemPublishedDate.text = news.publishedAt
//            Glide.with(itemView.context)
//                .load(news.urlToImage)
//                .apply(
//                    RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
//                )
//                .into(binding.imgPoster)
//            itemView.setOnClickListener {
//                onItemClick(news)
//            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<NewsEntity> =
            object : DiffUtil.ItemCallback<NewsEntity>() {
                override fun areItemsTheSame(oldUser: NewsEntity, newUser: NewsEntity): Boolean {
                    return oldUser.title == newUser.title
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldUser: NewsEntity, newUser: NewsEntity): Boolean {
                    return oldUser == newUser
                }
            }
    }
}

@Composable
fun NewsItem(
    urlToImage: String?,
    title: String,
    publishedAt: String,
    onItemClick: () -> Unit
) {
    ConstraintLayout(modifier = Modifier.clickable { onItemClick() }) {
        val (posterImage, titleText, publishedDateText) = createRefs()

        AsyncImage(
            model = urlToImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .constrainAs(posterImage) {
                    top.linkTo(parent.top)
                }
        )
        Text(
            text = title,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            maxLines = 3,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(titleText) {
                start.linkTo(posterImage.end, margin = 16.dp)
                top.linkTo(parent.top, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                width = Dimension.fillToConstraints
            }
        )
        Text(
            text = publishedAt,
            fontSize = 14.sp,
            modifier = Modifier.constrainAs(publishedDateText) {
                top.linkTo(titleText.bottom, margin = 8.dp)
                start.linkTo(titleText.start)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NewsItemPreview() {
    MaterialTheme {
        NewsItem("", "New News", "2022-02-22", {})
    }
}