package cu.nico.infinity.androidpagination.ui.passangers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cu.nico.infinity.androidpagination.R
import cu.nico.infinity.androidpagination.data.adapters.PassengersAdapter
import kotlinx.android.synthetic.main.fragment_passenger.view.*

class PassengersFragment : Fragment() {

    private lateinit var v: View
    private lateinit var passengersViewModel: PassengersViewModel
    private lateinit var layoutManager : LinearLayoutManager
    private var hasNext = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_passenger, container, false)
        setup()
        return v
    }

    private fun setup(){

        passengersViewModel = ViewModelProvider(this)[PassengersViewModel::class.java]
        layoutManager = LinearLayoutManager(requireContext())
        getOrders("0", false)
    }

    private fun getOrders(pageIndex: String, loadingMore : Boolean = false){
        if (!loadingMore){
           // loadingOrders(true)
        }
        passengersViewModel.getPassengersResponse(pageIndex)
        passengersViewModel.passengersResponse.observe(viewLifecycleOwner, { response ->
            if (!loadingMore){
                //loadingOrders(false)
            }
            if (response != null) {

                val adapter = PassengersAdapter(requireContext(), response)
                if (!loadingMore){
                    v.passengerRv.visibility = View.VISIBLE
                    v.passengerRv.adapter = adapter
                    v.passengerRv.layoutManager = LinearLayoutManager(requireContext())
                }
            }else{

            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PassengersFragment().apply {

            }
    }
}