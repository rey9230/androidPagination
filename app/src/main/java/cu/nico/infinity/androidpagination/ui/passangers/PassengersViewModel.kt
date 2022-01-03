package cu.nico.infinity.androidpagination.ui.passangers

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import cu.nico.infinity.androidpagination.data.MainRepository
import cu.nico.infinity.androidpagination.data.models.PassengersResponse

class PassengersViewModel(application: Application): AndroidViewModel(application){

    private var repository: MainRepository?=null
    var passengersResponse : MutableLiveData<PassengersResponse>

    val locationToShow : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        repository = MainRepository(application)
        passengersResponse = MutableLiveData()

    }

    fun getPassengersResponse(param : String){
        passengersResponse = repository?.getPassengers(param)!!
    }


}
