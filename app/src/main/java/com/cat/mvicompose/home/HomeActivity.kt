package com.cat.mvicompose.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

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
            MaterialTheme{
                listenToChanges()
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