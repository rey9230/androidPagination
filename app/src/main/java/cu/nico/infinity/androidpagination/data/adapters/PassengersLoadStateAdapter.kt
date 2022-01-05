package cu.nico.infinity.androidpagination.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import cu.nico.infinity.androidpagination.R

class PassengersLoadStateAdapter(private val retry: () -> Unit): LoadStateAdapter<PassengersLoadStateAdapter.PassengersLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: PassengersLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup,loadState: LoadState): PassengersLoadStateViewHolder {
        return PassengersLoadStateViewHolder.create(parent, retry)

    }

    class PassengersLoadStateViewHolder(itemView: View, retry: () -> Unit): RecyclerView.ViewHolder(itemView){
        private val errorTv: TextView = itemView.findViewById(R.id.error_msg)
        private val progressBar: ProgressBar = itemView.findViewById(R.id.progress_bar_paging)
        private val retryButton: Button = itemView.findViewById(R.id.retry_button)
        init {
            retryButton.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                errorTv.text = loadState.error.localizedMessage
            }
            progressBar.isVisible = loadState is LoadState.Loading
            retryButton.isVisible = loadState is LoadState.Error
            errorTv.isVisible = loadState is LoadState.Error
        }

        companion object {
            fun create(parent: ViewGroup, retry: () -> Unit): PassengersLoadStateViewHolder {
                val context = parent.context
                val inflater = LayoutInflater.from(context)
                val contactView = inflater.inflate(R.layout.passengers_load_state_footer_view_item, parent, false)
                //    val binding = ReposLoadStateFooterViewItemBinding.bind(view)
                return PassengersLoadStateViewHolder(contactView, retry)
            }
        }
    }
}
