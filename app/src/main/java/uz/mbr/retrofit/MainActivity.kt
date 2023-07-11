package uz.mbr.retrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.mbr.retrofit.databinding.ActivityMainBinding
import uz.mbr.retrofit.user.post.Post

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var rvAdapter: RvAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiClient = ApiClient()
        val apiService = apiClient.getRetrofit(this).create(ApiService::class.java)

        apiService.getAllPosts( "id", "desc").enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    rvAdapter = RvAdapter(body ?: emptyList())
                    binding.recyclerview.adapter = rvAdapter
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}