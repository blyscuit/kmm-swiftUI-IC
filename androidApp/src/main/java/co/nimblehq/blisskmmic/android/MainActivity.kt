package co.nimblehq.blisskmmic.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.nimblehq.blisskmmic.Greeting
import android.widget.TextView
import co.nimblehq.blisskmmic.di.koin.initKoin
import co.nimblehq.blisskmmic.domain.usecase.LogInUseCase
import co.nimblehq.blisskmmic.domain.usecase.SurveyListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    private val logInUseCase: LogInUseCase by inject()
    private val surveyListUseCase: SurveyListUseCase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()

        GlobalScope.launch (Dispatchers.Main) {
            logInUseCase("dev@nimblehq.co", "12345678")
                .collect {
                    surveyListUseCase(1)
                        .collect {

                        }
                }
        }
    }
}
