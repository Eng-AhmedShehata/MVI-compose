package com.cat.mvicompose.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CircleLoading(loadingState: Boolean) {
    if (loadingState) {
        Box(modifier = Modifier.fillMaxSize(), Alignment.Center) {
            CircularProgressIndicator(
                modifier = Modifier.size(35.dp),
                color = Color.Black
            )
        }
    }
}

@Composable
fun DisplayUsers(usersList: List<User>) {
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        //modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    )
    {

        items(usersList) { data ->
            DrawUserItem(data)
        }
    }
}

@Composable
private fun DrawUserItem(data: User) {
    Row(
        modifier = Modifier
            .background(Color.Gray)
            .fillMaxWidth()
            .clickable {

            }
            .padding(20.dp)

    ) {
        Text(text = data.name)
    }
}