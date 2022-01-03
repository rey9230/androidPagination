package cu.nico.infinity.androidpagination.data.models

import com.google.gson.annotations.SerializedName

data class PassengersResponse(
    @SerializedName("data") val data : ArrayList<PassengersDetails>,
    @SerializedName("totalPassengers") val totalPassengers  : Int = 0,
    @SerializedName("totalPages") val totalPages  : Int = 0)

data class PassengersDetails( @SerializedName("_id") val _id : String= "",
                              @SerializedName("name") val name : String?= "",
                              @SerializedName("trips") val trips : Int?= 0,
                              @SerializedName("airline") val airline : ArrayList<Airline>,
                              @SerializedName("__v") val __v : Int= 0)

data class Airline( @SerializedName("id") val id : Int= 0,
                              @SerializedName("name") val name : String= "",
                              @SerializedName("country") val country : String= "",
                              @SerializedName("logo") val logo : String= "",
                              @SerializedName("slogan") val slogan : String= "",
                              @SerializedName("head_quaters") val head_quaters : String= "",
                              @SerializedName("website") val website : String= "",
                              @SerializedName("established") val established : String= "")

