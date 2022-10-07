package com.example.myjetpack_recycler.ui.screen

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.myjetpack_recycler.R
import com.example.myjetpack_recycler.data.Articles
import com.example.myjetpack_recycler.ui.theme.Myjetpack_recyclerTheme
import com.example.myjetpack_recycler.viewmodel.NewsViewModel

class MainActivity : ComponentActivity() {

    private val newsViewModel = NewsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Myjetpack_recyclerTheme {

                Scaffold(backgroundColor = Color.White, topBar = { MyTopBar() }) {

                    ShowRecycler(list = newsViewModel.newsList)
                    newsViewModel.fetchNews("in")

                }

            }
        }
    }
}

@Composable
fun ShowRecycler(list: List<Articles>) {

    if (list.isEmpty()) {
        Column(
            modifier = Modifier.background(color = Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.nonews),
                contentDescription = null,
                modifier = Modifier.padding(vertical = 5.dp)
            )
            Text(
                text = "No News found",
                textAlign = TextAlign.Center,
                style = TextStyle(fontWeight = FontWeight.Bold, color = Color.Black)
            )
        }
    } else {

        LazyColumn(modifier = Modifier.background(color = Color.White)) {
            items(list) { anews ->
                run {
                    Log.d("news", anews.toString())
                    NewsItemUi(anews)
                }
            }
        }
    }

}

@Composable
fun NewsItemUi(articles: Articles) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(all = 10.dp),
        backgroundColor = Color.White

    ) {
        Column(
            modifier = Modifier
                .background(color = Color.White)
        ) {
            articles.title?.let {
                Box(modifier = Modifier.background(color = colorResource(id = R.color.title_bg))) {
                    Text(
                        text = it,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(all = 10.dp)

                    )
                }
            }
            articles.description?.let {
                Text(
                    text = it,
                    style = TextStyle(color = Color.Black, fontSize = 15.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Image(

                painter = rememberImagePainter(data = articles.urlToImage, builder = {}),

                contentDescription = null,

                modifier = Modifier
                    .width(400.dp)
                    .height(200.dp)

            )
            Text(
                text = articles.publishedAt.toString(), style = TextStyle(
                    color = Color.Blue, fontSize = 10.sp, fontStyle = FontStyle.Italic
                ), modifier = Modifier.padding(all = 5.dp)
            )

        }
    }


}


@Composable
fun MyTopBar() {

    Box(modifier = Modifier.padding(all = 10.dp)) {
        Text(
            text = "NewsApp",
            style = TextStyle(fontSize = 20.sp, color = Color.Black, fontWeight = FontWeight.Bold)
        )
    }
}
