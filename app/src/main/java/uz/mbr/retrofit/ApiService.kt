package uz.mbr.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.mbr.retrofit.user.User
import uz.mbr.retrofit.user.post.Post

interface ApiService {
    @GET("users")
    fun getAllUsers(): Call<List<User>>

    @GET("users/{id}")
    fun getUser(@Path("id") userId: Int): Call<User>

    @GET("posts")
    fun getAllPosts(
        @Query("_sort") sort: String,
        @Query("_order") order: String
    ): Call<List<Post>>

    @GET("posts")
    fun getPostsByUser(
        @Query("userId") userId: Int,
        @Query("_sort") sort: String,
        @Query("_order") order: String
    ): Call<List<Post>>

}