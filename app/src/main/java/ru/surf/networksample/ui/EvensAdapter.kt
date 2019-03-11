package ru.surf.networksample.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_event.view.*
import ru.surf.networksample.R
import ru.surf.networksample.domain.Event


class EvensAdapter : RecyclerView.Adapter<EvensAdapter.EventsViewHolder>() {

    private var items: List<Event> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        return EventsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_event, parent, false)
        )
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun setItems(events: List<Event>) {
        items = events
        notifyDataSetChanged()
    }

    class EventsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(event: Event) {
            itemView.event_title.text = event.title
        }
    }
}