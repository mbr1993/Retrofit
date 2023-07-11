package uz.mbr.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.mbr.retrofit.databinding.ActivityDetailBinding
import uz.mbr.retrofit.user.User

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent?.getIntExtra("id", 0)
        val apiClient = ApiClient()
        val apiService = apiClient.getRetrofit().create(ApiService::class.java)

        apiService.getUser(id!!).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    binding.progressbar.visibility = View.INVISIBLE
                    val body = response.body()
                    binding.username.text = body?.username
                    binding.email.text = body?.email
                    binding.phone.text = body?.phone
                    binding.website.text = body?.website
                    binding.city.text = body?.address?.city
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                binding.progressbar.visibility = View.INVISIBLE
                Toast.makeText(this@DetailActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}