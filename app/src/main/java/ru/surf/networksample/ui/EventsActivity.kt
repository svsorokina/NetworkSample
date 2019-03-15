package ru.surf.networksample.ui

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import ru.surf.networksample.App
import ru.surf.networksample.R
import ru.surf.networksample.base.DaggerViewModelsFactory
import ru.surf.networksample.base.ScreenState
import ru.surf.networksample.ui.di.DaggerEventsScreenComponent
import javax.inject.Inject

class EventsActivity : AppCompatActivity(), LifecycleOwner {

    @Inject
    lateinit var viewModeFactory: DaggerViewModelsFactory

    private lateinit var viewModel: EventsViewModel
    private lateinit var adapter: EvensAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponent()
        initAdapter()

        viewModel = ViewModelProviders.of(this, viewModeFactory).get(EventsViewModel::class.java)

        viewModel.eventsState.observe(
            this,
            Observer { updateUI(it) }
        )
    }

    private fun initComponent() {
        DaggerEventsScreenComponent.builder()
            .appComponent((application as App).component)
            .build()
            .inject(this)
    }

    private fun updateUI(screenState: ScreenState<EventsState>) {
        when (screenState) {
            ScreenState.Loading -> showProgress()
            is ScreenState.Error -> showErrorMessage(screenState.apiException.message)
            is ScreenState.Render -> processRenderState(screenState.renderState)
        }
    }

    private fun processRenderState(renderState: EventsState) {
        hideProgress()
        when (renderState) {
            is EventsState.EventsLoaded -> adapter.setItems(renderState.events)
        }
    }

    private fun showProgress() {
        progress_bar.visibility = View.VISIBLE
        rv_events.visibility = View.GONE
    }

    private fun hideProgress() {
        progress_bar.visibility = View.GONE
        rv_events.visibility = View.VISIBLE
    }

    private fun showErrorMessage(message: String) {
        hideProgress()
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun initAdapter() {
        rv_events.layoutManager = LinearLayoutManager(this)
        rv_events.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        adapter = EvensAdapter()
        rv_events.adapter = adapter
    }
}
