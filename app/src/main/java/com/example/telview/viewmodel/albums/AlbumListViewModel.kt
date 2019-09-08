package com.example.telview.viewmodel.albums

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.BindingAdapter
import android.widget.ImageView
import com.example.telview.model.AlbumsDTO
import com.example.telview.model.ImageUrls
import com.example.telview.model.Users
import com.squareup.picasso.Picasso


class AlbumListViewModel : ViewModel() {

    private val postTitle = MutableLiveData<String>()
    private val postByUser = MutableLiveData<String>()
    private var imageId = MutableLiveData<String>()

    fun bind(albums: AlbumsDTO) {
        postTitle.value = albums.title
        postByUser.value = "- by " + Users.getUsername(albums.userId!!).toString()
        imageId.value = ImageUrls.getThumnailImages(albums.id!!)
    }

    fun getPostTitle(): MutableLiveData<String> {
        return postTitle
    }

    fun getPostByUser(): MutableLiveData<String> {
        return postByUser
    }

    fun getImageId(): MutableLiveData<String> {
        return imageId
    }

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun setImageUrl(view: ImageView, imageUrl: String) {
            Picasso.get().load(imageUrl).into(view)
        }
    }
}