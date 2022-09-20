package co.nimblehq.blisskmmic.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import co.nimblehq.blisskmmic.Greeting
import android.widget.TextView
import co.nimblehq.blisskmmic.data.network.core.NetworkClient
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    private val mainScope = MainScope()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        mainScope.launch {

            kotlin.runCatching {
                NetworkClient().getAllLaunches()
            }.onSuccess {
                Log.e("", it.title)
            }.onFailure {
                Log.e("", it.toString())
            }
            tv.text = greet()
        }
    }
}
