package uz.mbr.retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.mbr.retrofit.databinding.RowItemBinding
import uz.mbr.retrofit.user.User
import uz.mbr.retrofit.user.post.Post

class RvAdapter(private val list: List<Post>) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: RowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(post: Post) {
            binding.textView.text = "${post.id} ${post.title}"
            binding.textView2.text = post.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }
}