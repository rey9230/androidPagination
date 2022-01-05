package cu.nico.infinity.androidpagination.api

import cu.nico.infinity.androidpagination.data.models.PassengersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/v1/passenger")
    suspend fun getPassengers(@Query("page") page : String, @Query("size") size : String) : PassengersResponse

}

