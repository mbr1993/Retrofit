package uz.mbr.retrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.mbr.retrofit.databinding.ActivityMainBinding
import uz.mbr.retrofit.user.User

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var rvAdapter: RvAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiClient = ApiClient()
        val apiService = apiClient.getRetrofit().create(ApiService::class.java)

        apiService.getAllUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    val list: List<User>? = response.body()
                    rvAdapter = RvAdapter(list ?: emptyList())
                    binding.recyclerview.adapter = rvAdapter

                    val itemDecoration =
                        DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
                    binding.recyclerview.addItemDecoration(itemDecoration)

                    rvAdapter?.setOnUserClickedListener {
                        val intent = Intent(this@MainActivity, DetailActivity::class.java)
                        intent.putExtra("id", list?.get(it)?.id)
                        startActivity(intent)
                    }
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}