package com.android.myweatherapp.rx

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import java.util.concurrent.Flow

@Suppress("UNCHECKED_CAST")
object AppRxSchedulers {

    //  Observable transformer
    private var transformer: ObservableTransformer<Any, Any> = ObservableTransformer { upstream ->
        upstream
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }


    internal fun <T> applySchedulers(): ObservableTransformer<T, T> {
        return transformer as ObservableTransformer<T, T>
    }

    //  Flowable transformer
    private val flowableTransformer: FlowableTransformer<Any, Any> = FlowableTransformer { upstream ->
        upstream
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    internal fun <T> applyFlowableSchedulers(): FlowableTransformer<T, T> {
        return flowableTransformer as FlowableTransformer<T, T>
    }

    // Single transformer
    private var singleTransformers: SingleTransformer<Any, Any> = SingleTransformer { upstream ->
        upstream
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    internal fun <T> applySingleSchedulers(): SingleTransformer<T, T> {
        return singleTransformers as SingleTransformer<T, T>
    }

}