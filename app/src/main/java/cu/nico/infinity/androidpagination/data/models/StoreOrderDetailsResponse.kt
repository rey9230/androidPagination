package cu.nico.infinity.androidpagination.data.models

import com.google.gson.annotations.SerializedName

data class StoreOrderDetailsResponse(
    @SerializedName("data") val data : StoreOrderDetails,
    @SerializedName("success") val success : Boolean = true,
    @SerializedName("message") val message : String = "",
    @SerializedName("actionCode") val actionCode : Int = -1)

data class StoreOrderDetails( @SerializedName("type") val type : Int= 0,
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
                              @SerializedName("totalItems") val totalItems : Int= 0,
                              @SerializedName("discount") val discount : Int= 0,
                              @SerializedName("discountToShow") val discountToShow : String= "",
                              @SerializedName("shippingPrice") val shippingPrice : Double= 0.0,
                              @SerializedName("shippingPriceToShow") val shippingPriceToShow : String= "",
                              @SerializedName("subTotalPrice") val subTotalPrice : Double= 0.0,
                              @SerializedName("subTotalPriceToShow") val subTotalPriceToShow : String= "",
                              @SerializedName("items") val items : ArrayList<StoreOrderItems>)

data class StoreOrderItems(  @SerializedName("code") val code : String= "",
                             @SerializedName("name") val name : String= "",
                             @SerializedName("shortText") val shortText : String= "",
                             @SerializedName("price") val price : Double= 0.0,
                             @SerializedName("discount") val discount : Double= 0.0,
                             @SerializedName("shippingPrice") val shippingPrice : Double= 0.0,
                             @SerializedName("specifications") val specifications : ArrayList<String>,
                             @SerializedName("priceToShow") val priceToShow : String= "",
                             @SerializedName("oldPriceToShow") val oldPriceToShow : String= "",
                             @SerializedName("imageUrl") val imageUrl : String= "",
                             @SerializedName("previewUrl") val previewUrl : String= "",
                             @SerializedName("statusToShow") val statusToShow : String= "",
                             @SerializedName("status") val status : Int= 0,
                             @SerializedName("quantity") val quantity : Int= 0,
                             @SerializedName("quantityToShow") val quantityToShow : String= "")



