package cu.nico.infinity.androidpagination.data.models

import com.google.gson.annotations.SerializedName

data class StoreOrdersResponse(@SerializedName("pageIndex") val pageIndex : Int = 0,
                           @SerializedName("pageSize") val pageSize : Int = 0,
                           @SerializedName("totalPages") val totalPages : Int = 0,
                           @SerializedName("hasPrevious") val hasPrevious : Boolean=false,
                           @SerializedName("hasNext") val hasNext : Boolean=false,
                           @SerializedName("totalCount") val totalCount : Int = 0,
                           @SerializedName("data") val data : ArrayList<StoreOrderItem>,
                           @SerializedName("success") val success : Boolean = true,
                           @SerializedName("message") val message : String = "",
                           @SerializedName("actionCode") val actionCode : Int = -1)

data class StoreOrderItem(@SerializedName("type") val type : Int= 0,
                              @SerializedName("orderCode") val orderCode : String= "",
                              @SerializedName("purchaseDate") val purchaseDate : String= "",
                              @SerializedName("purchaseDateToShow") val purchaseDateToShow : String= "",
                              @SerializedName("totalPrice") val totalPrice : Double= 0.0,
                              @SerializedName("totalPriceToShow") val totalPriceToShow : String= "",
                              @SerializedName("status") val status : Int= 0,
                              @SerializedName("statusToShow") val statusToShow : String= "",
                              @SerializedName("paymentData") val paymentData : String= "",
                              @SerializedName("cardType") val cardType : Int= 0,
                              @SerializedName("locationToShow") val locationToShow : String= "",
                              @SerializedName("totalItems") val totalItems : Int= 0)


