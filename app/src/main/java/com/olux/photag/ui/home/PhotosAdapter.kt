package com.olux.photag.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olux.photag.databinding.ViewPhotolistItemBinding
import com.olux.photag.models.Photo
import com.olux.photag.ui.photoDetail.PhotoDetailActivity

class PhotosAdapter : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {
    private val photos = mutableListOf<Photo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ViewPhotolistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    fun setPhotos(photos: List<Photo>) {
        this.photos.apply {
            clear()
            addAll(photos)
        }

        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ViewPhotolistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, PhotoDetailActivity::class.java)
                intent.putExtra(PhotoDetailActivity.EXTRA_PHOTO_ID, binding.photo?.id)
                binding.root.context.startActivity(intent)
            }
        }

        fun bind(photo: Photo) {
            binding.photo = photo
        }
    }
}