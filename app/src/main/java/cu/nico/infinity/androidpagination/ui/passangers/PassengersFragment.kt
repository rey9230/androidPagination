package cu.nico.infinity.androidpagination.ui.passangers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import cu.nico.infinity.androidpagination.R
import cu.nico.infinity.androidpagination.data.adapters.PassengersAdapter
import cu.nico.infinity.androidpagination.data.adapters.PassengersLoadStateAdapter
import kotlinx.android.synthetic.main.fragment_passenger.view.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter

class PassengersFragment : Fragment() {

    private lateinit var v: View
    private lateinit var passengersViewModel: PassengersViewModel
    private lateinit var layoutManager : LinearLayoutManager
    private var hasNext = false

    private lateinit var adapter:PassengersAdapter
    private var searchJob: Job? = null

    private fun search() {
        // Make sure we cancel the previous job before creating a new one
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            passengersViewModel.searchPassengers().collect {
                adapter.submitData(it)
            }
        }
    }
    private fun initSearch(){
        lifecycleScope.launch {
            adapter.loadStateFlow
                // Only emit when REFRESH LoadState for RemoteMediator changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where Remote REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { v.passengerRv.scrollToPosition(0) }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_passenger, container, false)
        setup()
        search()
        initSearch()
        return v
    }

    private fun setup(){
        adapter = PassengersAdapter(requireContext())
        passengersViewModel = ViewModelProvider(this)[PassengersViewModel::class.java]
        layoutManager = LinearLayoutManager(requireContext())
        v.passengerRv.layoutManager = layoutManager
        v.passengerRv.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PassengersLoadStateAdapter{adapter.retry()},
            footer = PassengersLoadStateAdapter{adapter.retry()}
        )

    }

//    private fun getOrders(pageIndex: String, loadingMore : Boolean = false){
//        if (!loadingMore){
//           // loadingOrders(true)
//        }
//        passengersViewModel.getPassengersResponse(pageIndex)
//        passengersViewModel.passengersResponse.observe(viewLifecycleOwner, { response ->
//            if (!loadingMore){
//                //loadingOrders(false)
//            }
//            if (response != null) {
//
//                val adapter = PassengersAdapter(requireContext())
//                if (!loadingMore){
//                    v.passengerRv.visibility = View.VISIBLE
//                    v.passengerRv.adapter = adapter
//                    v.passengerRv.layoutManager = LinearLayoutManager(requireContext())
//                }
//            }else{
//
//            }
//        })
//    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PassengersFragment().apply {

            }
    }
}