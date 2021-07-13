package com.android.myweatherapp.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.android.myweatherapp.GDelegate
import com.android.myweatherapp.repository.AppRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var appRepository: AppRepository

    init {
        GDelegate.INSTANCE.appComponent.inject(this)
    }

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}