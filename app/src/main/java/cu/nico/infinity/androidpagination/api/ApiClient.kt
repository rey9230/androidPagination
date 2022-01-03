package cu.nico.infinity.androidpagination.api

import android.content.Context
import com.google.gson.GsonBuilder
import cu.nico.infinity.androidpagination.R
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    companion object {
        private var retrofit: Retrofit? = null
        fun getApiClient(context: Context): Retrofit {
            val gson = GsonBuilder()
                    .setLenient()
                    .create()
            val okHttpClient = OkHttpClient.Builder()
                    .readTimeout(2, TimeUnit.MINUTES)
                    .connectTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(2, TimeUnit.MINUTES)
                    .build()

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(context.getString(R.string.web_service_endpoint))
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()
            }
            return retrofit!!
        }

    }
}