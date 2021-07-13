package com.android.myweatherapp.data.model.api

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class ErrorLiveData
@Inject
constructor(private val executors: AppExecutors): LiveData<NetworkError>() {
    // multiple threads to check and change the boolean
    private val pending = AtomicBoolean(false)

    fun setNetworkError(value : NetworkError) {
        executors.mainThread().execute {
            pending.set(true)
            super.setValue(value)
        }
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<in NetworkError>) {
        super.observe(owner, Observer<NetworkError> { value ->
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(value)
            }
        })
    }
}