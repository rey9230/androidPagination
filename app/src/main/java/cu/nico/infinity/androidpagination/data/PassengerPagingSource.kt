package cu.nico.infinity.androidpagination.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import cu.nico.infinity.androidpagination.api.ApiInterface
import cu.nico.infinity.androidpagination.data.models.PassengersDetails
import retrofit2.HttpException
import retrofit2.awaitResponse
import java.io.IOException

const val PASSENGERS_STARTING_PAGE_INDEX = 0
const val NETWORK_PAGE_SIZE = 10

class PassengerPagingSource(private val service:ApiInterface): PagingSource<Int,PassengersDetails>() {
    override fun getRefreshKey(state: PagingState<Int, PassengersDetails>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PassengersDetails> {
        val position = params.key ?: PASSENGERS_STARTING_PAGE_INDEX
        return try {
            val response = service.getPassengers(position.toString(), params.loadSize.toString())
            val passengers = response.data
            val nextKey = if (passengers.isEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = passengers,
                prevKey = if (position == PASSENGERS_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }

    }
}