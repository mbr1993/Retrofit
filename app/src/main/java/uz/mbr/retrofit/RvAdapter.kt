package uz.mbr.retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.mbr.retrofit.databinding.RowItemBinding
import uz.mbr.retrofit.user.User

class RvAdapter(private val list: List<User>) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    private var listener: OnUserClickedListener? = null

    inner class ViewHolder(private val binding: RowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(user: User) {
            binding.textView.text = user.name
            binding.textView2.text = user.phone
            binding.root.setOnClickListener {
                listener?.onUserClicked(adapterPosition)
            }
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

    fun setOnUserClickedListener(listener: OnUserClickedListener) {
        this.listener = listener
    }

    fun interface OnUserClickedListener {
        fun onUserClicked(position: Int)
    }
}