package cu.nico.infinity.androidpagination.data.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cu.nico.infinity.androidpagination.R
import cu.nico.infinity.androidpagination.data.models.PassengersResponse
import cu.nico.infinity.androidpagination.interfaces.PaginationAdapterCallback

class PassengersAdapter(
    val context: Context,
    private val passengersResponse: PassengersResponse
) :
    RecyclerView.Adapter<PassengersAdapter.ViewHolder>(),
    PaginationAdapterCallback {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTv = itemView.findViewById<TextView>(R.id.nameTv)
        val tripsTv = itemView.findViewById<TextView>(R.id.tripsTv)
        val airlinesRv = itemView.findViewById<RecyclerView>(R.id.airlinesRv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_passenger_card, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nameTv = holder.nameTv
        val tripsTv = holder.tripsTv
        val airlinesRv = holder.airlinesRv

        val passengers = passengersResponse.data[position]

        if (passengers.name != null) {
            nameTv.text = passengers.name
        } else {
            nameTv.text = context.getString(R.string.unavailable)
        }

        if (passengers.trips != null) {
            tripsTv.text = passengers.trips.toString()
        } else {
            tripsTv.text = context.getString(R.string.unavailable)
        }

        val adapter = AirlinesAdapter(context, passengers.airline)
        airlinesRv.visibility = View.VISIBLE
        airlinesRv.adapter = adapter
        airlinesRv.layoutManager = LinearLayoutManager(context)

    }

    override fun getItemCount(): Int {
        return passengersResponse.data.size
    }


    override fun retryPageLoad() {
    }
}