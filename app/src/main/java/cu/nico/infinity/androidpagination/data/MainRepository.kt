package cu.nico.infinity.androidpagination.data

import android.content.Context
import androidx.lifecycle.MutableLiveData
import cu.nico.infinity.androidpagination.api.ApiClient
import cu.nico.infinity.androidpagination.api.ApiInterface
import cu.nico.infinity.androidpagination.data.models.PassengersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository (context: Context){

    private var apiInterface: ApiInterface?=null
    var context : Context

    init {
        apiInterface = ApiClient.getApiClient(context).create(ApiInterface::class.java)
        this.context = context
    }

    fun getPassengers(param : String) : MutableLiveData<PassengersResponse> {
        val data = MutableLiveData<PassengersResponse>()
        apiInterface?.getPassengers(param,  "10")?.enqueue(object : Callback<PassengersResponse> {
            override fun onResponse(call: Call<PassengersResponse>, response: Response<PassengersResponse>) {
                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    data.value = res
                }else{
                    data.value = null
                }
            }
            override fun onFailure(call: Call<PassengersResponse>, t: Throwable) {
                println("Error $t")
                data.value = null
            }
        })
        return data
    }


}
