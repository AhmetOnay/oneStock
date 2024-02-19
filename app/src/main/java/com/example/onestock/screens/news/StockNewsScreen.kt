package com.example.onestock.screens.news

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.onestock.models.NewsArticle
import com.example.onestock.viewmodels.InjectorUtils
import com.example.onestock.viewmodels.StockNewsViewModel
import com.example.onestock.widgets.CustomScaffold


@Composable
fun StockNewsScreen(navController: NavHostController) {
    val viewModel: StockNewsViewModel = viewModel(
        factory = InjectorUtils.provideStockNewsViewModelFactory()
    )

    var selectedTabIndex by remember { mutableStateOf(0) }
    var tabs = listOf("News")
    val newsData by viewModel.stockNewsData.observeAsState()


    CustomScaffold(
        navController = navController,
        topBarTitle = "News",
    ) { innerPadding ->
        newsData?.let { news ->
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                items(news.data.size) { index ->
                    val newsArticle = news.data[index]
                    NewsArticleItem(article = newsArticle)
                }
            }
        } ?: Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Loading news...")
        }
    }
}

@Composable
fun NewsArticleItem(article: NewsArticle) {
    val context = LocalContext.current
    Card(modifier = Modifier.padding(8.dp).fillMaxWidth(), elevation = 4.dp) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = article.title, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = article.description, style = MaterialTheme.typography.body2)
            Spacer(modifier = Modifier.height(8.dp))
            val annotatedLinkString = AnnotatedString.Builder("Read more").apply {
                addStyle(style = TextStyle(color = Color.Blue, textDecoration = TextDecoration.Underline).toSpanStyle(), start = 0, end = 8)
            }.toAnnotatedString()

            ClickableText(
                text = annotatedLinkString,
                onClick = {
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(article.url)))
                },
                style = TextStyle(color = Color.Blue, textDecoration = TextDecoration.Underline)
            )
        }
    }
}