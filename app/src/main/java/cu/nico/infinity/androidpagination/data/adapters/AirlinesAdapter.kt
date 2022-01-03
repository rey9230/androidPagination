package cu.nico.infinity.androidpagination.data.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import cu.nico.infinity.androidpagination.R
import cu.nico.infinity.androidpagination.data.models.Airline
import cu.nico.infinity.androidpagination.interfaces.PaginationAdapterCallback

class AirlinesAdapter(
    val context: Context,
    private val airlineList: ArrayList<Airline>
) :
    RecyclerView.Adapter<AirlinesAdapter.ViewHolder>(),
    PaginationAdapterCallback {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val airlineLogo = itemView.findViewById<ImageView>(R.id.airlineLogo)
        val nameTv = itemView.findViewById<TextView>(R.id.nameTv)
        val sloganTv = itemView.findViewById<TextView>(R.id.sloganTv)
        val headQuartersTv = itemView.findViewById<TextView>(R.id.headQuartersTv)
        val webTv = itemView.findViewById<TextView>(R.id.webTv)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.item_airline_card, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val airlineLogo = holder.airlineLogo
        val nameTv = holder.nameTv
        val sloganTv = holder.sloganTv
        val headQuartersTv = holder.headQuartersTv
        val webTv = holder.webTv

        val passengers = airlineList[position]

        nameTv.text = passengers.name
        sloganTv.text = passengers.slogan
        headQuartersTv.text = passengers.head_quaters
        webTv.text = passengers.website

        Glide
            .with(context)
            .load(passengers.logo)
            .into(airlineLogo)

    }
    override fun getItemCount(): Int {
        return airlineList.size
    }

    override fun retryPageLoad() {
        //listener.loadNextPage()
    }
}