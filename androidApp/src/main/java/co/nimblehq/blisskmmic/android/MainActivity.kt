package co.nimblehq.blisskmmic.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import co.nimblehq.blisskmmic.Greeting
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import co.nimblehq.blisskmmic.BreedViewState
import co.nimblehq.blisskmmic.LoginViewModel
import co.nimblehq.blisskmmic.data.network.core.NetworkClient

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    val viewModel = LoginViewModel(NetworkClient())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Column {
                    GreetingScreen("user1", viewModel)
                    GreetingScreen("user2", viewModel)
                }
            }
        }
    }
}

@Composable
fun GreetingScreen(
    userId: String,
    viewModel: LoginViewModel
    ) {
    val state by viewModel.breedState.collectAsState(initial = BreedViewState())

    Text(text = userId)
    Column {
        for (name in state.breeds?: listOf("")) {
            Log.e("", "${state.breeds?.count()}")
            Text(name)
        }
    }
}
