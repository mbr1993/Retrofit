package uz.mbr.retrofit

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private val baseUrl = "https://jsonplaceholder.typicode.com/"

    fun getRetrofit(context: Context): Retrofit {
        val chuckerInterceptor = ChuckerInterceptor(context)


        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(chuckerInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}