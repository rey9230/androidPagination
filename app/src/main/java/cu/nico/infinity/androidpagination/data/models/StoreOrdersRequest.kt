package cu.nico.infinity.androidpagination.data.models

import com.google.gson.annotations.SerializedName


data class StoreOrdersRequest(
    @SerializedName("pageIndex") var pageIndex: Int = 1,
    @SerializedName("pageSize") var pageSize: Int = 6,
    @SerializedName("pagingStrategy") var pagingStrategy: Int = 0)