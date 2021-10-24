package com.cat.mvicompose.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.cat.mvicompose.R
// Ui layer to display data
class HomeActivity : AppCompatActivity() {

    //1
    private val viewModel: HomeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1
        requestUsersIntent()
        //2
        setContent {
            MaterialTheme {
                //listenToChanges()

                val usersList = mutableListOf<User>()
                repeat(200) {
                    usersList.add(User())
                }
                val listState = rememberLazyListState()

                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize()
                )
                {

                    items(usersList) { user ->
                        Text(text = user.name, fontSize = 20.sp)
                    }
                }

                /*Column {
                    val isVisible = remember {
                        mutableStateOf(true)
                    }
                    Box(modifier = Modifier.size(200.dp)) {
                        if (isVisible.value) {
                            Image(painter = painterResource(id = R.drawable.android),
                                contentDescription = "")
                        }
                    }
                    Button(onClick = {
                        //...
                        isVisible.value = !isVisible.value
                    }) {
                        Text(text = "CLick me", fontSize = 20.sp)
                    }
                }*/
                /*Row(
                    modifier = Modifier
                        .background(Color.Cyan)
                        .clickable {

                        }
                        .padding(20.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .fillMaxWidth(),
                ) {
                    Text(text = "Hi", fontSize = 20.sp)
                    Text(text = "Hi", fontSize = 20.sp)
                    Text(text = "Hi", fontSize = 20.sp)
                }*/
            }
        }

    }

    @Composable
    private fun listenToChanges() {
        val state = viewModel.stateChannel.collectAsState()
        val stateValue = state.value

        CircleLoading(loadingState = stateValue.isLoading)

        if (stateValue.usersList != null) {
            // display data
            DisplayUsers(usersList = stateValue.usersList!!)

        }

    }

    private fun requestUsersIntent() {
        lifecycleScope.launch {
            viewModel.intentChannel.send(HomeIntent.GetUsers)
        }
    }

}