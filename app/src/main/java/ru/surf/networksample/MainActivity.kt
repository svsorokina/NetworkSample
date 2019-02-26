package ru.surf.networksample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.surf.networksample.network.EventsResponse
import ru.surf.networksample.network.ResponseCallback

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EventsRepository.instance.getEvents(object : ResponseCallback<EventsResponse> {

            override fun onSuccess(apiResponse: EventsResponse) {
                text.text = apiResponse.events.joinToString(separator = "\n", transform = { event -> event.title })
            }

            override fun onFailure(errorMessage: String) {
                text.text = errorMessage
            }
        })
    }
}
