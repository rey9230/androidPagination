package cu.nico.infinity.androidpagination.data.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cu.nico.infinity.androidpagination.R
import cu.nico.infinity.androidpagination.data.models.PassengersDetails

class PassengersAdapter(private val context: Context): PagingDataAdapter<PassengersDetails, PassengersAdapter.PassengerViewHolder>(REPO_COMPARATOR){

    inner class PassengerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTv = itemView.findViewById<TextView>(R.id.nameTv)
        val tripsTv = itemView.findViewById<TextView>(R.id.tripsTv)
        val airlinesRv = itemView.findViewById<RecyclerView>(R.id.airlinesRv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassengerViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_passenger_card, parent, false)
        return PassengerViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: PassengerViewHolder, position: Int) {
        val nameTv = holder.nameTv
        val tripsTv = holder.tripsTv
        val airlinesRv = holder.airlinesRv

        val passenger = getItem(position)

        if (passenger!!.name != null) {
            nameTv.text = passenger.name
        } else {
            nameTv.text = context.getString(R.string.unavailable)
        }

        if (passenger.trips != null) {
            tripsTv.text = passenger.trips.toString()
        } else {
            tripsTv.text = context.getString(R.string.unavailable)
        }

        val adapter = AirlinesAdapter(context, passenger.airline)
        airlinesRv.visibility = View.VISIBLE
        airlinesRv.adapter = adapter
        airlinesRv.layoutManager = LinearLayoutManager(context)

    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<PassengersDetails>() {
            override fun areItemsTheSame(oldItem: PassengersDetails, newItem: PassengersDetails): Boolean =
                oldItem._id == newItem._id

            override fun areContentsTheSame(oldItem: PassengersDetails, newItem: PassengersDetails): Boolean =
                oldItem == newItem
        }
    }
}