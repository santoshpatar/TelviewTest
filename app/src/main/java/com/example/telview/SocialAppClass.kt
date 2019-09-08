package com.example.telview

import android.app.Application
import com.example.telview.model.AlbumPhotosDTO
import com.example.telview.model.ImageUrls
import com.example.telview.model.Users
import com.example.telview.model.UsersDTO
import com.example.telview.networkutil.RetrofitFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SocialAppClass : Application() {

    private var compositeDisposable = CompositeDisposable()

    override fun onCreate() {
        super.onCreate()

        val service = RetrofitFactory.makeRetrofitService()
        val usersResponse = service.getUsers()
        val albumUrlResponse = service.getAlbumsbyId()

        compositeDisposable.add(
            usersResponse.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(
                { res -> setUsers(res) },
                { e -> print(e) })
        )

        compositeDisposable.add(
            albumUrlResponse.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(
                { res -> setImages(res) },
                { e -> print(e) })
        )
    }


    private fun setImages(images: ArrayList<AlbumPhotosDTO>?) {
        ImageUrls.imageList = images!!
    }

    private fun setUsers(users: ArrayList<UsersDTO>?) {
        Users.userList = users!!
    }
}