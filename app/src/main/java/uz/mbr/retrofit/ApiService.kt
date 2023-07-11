package uz.mbr.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.mbr.retrofit.user.User

interface ApiService {
    @GET("users")
    fun getAllUsers(): Call<List<User>>

    @GET("users/{id}")
    fun getUser(@Path("id") userId: Int): Call<User>
}