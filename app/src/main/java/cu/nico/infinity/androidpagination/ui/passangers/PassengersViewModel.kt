package cu.nico.infinity.androidpagination.ui.passangers

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import cu.nico.infinity.androidpagination.data.MainRepository
import cu.nico.infinity.androidpagination.data.models.PassengersDetails
import cu.nico.infinity.androidpagination.data.models.PassengersResponse
import kotlinx.coroutines.flow.Flow

class PassengersViewModel(application: Application): AndroidViewModel(application){

    private var currentSearchResult: Flow<PagingData<PassengersDetails>>? = null

    private var repository: MainRepository?=null
    var passengersResponse : MutableLiveData<PassengersResponse>

    val locationToShow : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        repository = MainRepository(application)
        passengersResponse = MutableLiveData()
    }

//    fun getPassengersResponse(param : String){
//        passengersResponse = repository?.getPassengers(param)!!
//    }
    fun searchPassengers(): Flow<PagingData<PassengersDetails>> {
        val newResult: Flow<PagingData<PassengersDetails>> = repository!!.getSearchPassengers().cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }



}
