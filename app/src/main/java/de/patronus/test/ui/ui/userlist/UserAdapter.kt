package de.patronus.test.ui.ui.userlist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import de.patronus.test.databinding.ItemUserBinding
import de.patronus.test.domain.models.User
import de.patronus.test.ui.base.BaseAdapter
import javax.inject.Inject

class UserAdapter @Inject constructor(private val glide: RequestManager) : BaseAdapter<User>() {

    private val diffCallback = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override val differ = AsyncListDiffer(this, diffCallback)


    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root), Binder<User> {
        override fun bind(item: User) {
            binding.apply {
                itemUserTvName.text = item.firstName + " " + item.lastName
                itemUserTvGender.text = item.gender
                itemUserTvPhone.text = item.phoneNumber
                glide.load(item.image).into(itemUserIvPicture)
                root.setOnClickListener {
                    onItemClickListener?.let { itemClick ->
                        itemClick(item)
                    }
                }
                if (item.stickers != null) {
                    if (item.stickers!!.contains("Fam")) {
                        itemUserTvFam.visibility = View.VISIBLE
                    } else {
                        itemUserTvFam.visibility = View.GONE
                    }
                    if (item.stickers!!.contains("Ban")) {
                        itemUserTvBan.visibility = View.VISIBLE
                    } else {
                        itemUserTvBan.visibility = View.GONE
                    }
                    val opacity = (0.5 * 255).toInt()
                    binding.itemUserTvBan.background.alpha = opacity
                }
            }
        }
    }

}