package com.android.myweatherapp.di.modules

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import com.android.myweatherapp.GDelegate
import com.android.myweatherapp.data.remote.AppAPIs
import com.android.myweatherapp.repository.AppRepository
import com.android.myweatherapp.util.ConstantsUtil
import com.android.myweatherapp.util.GLog
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
open class ApplicationModule(private val application: GDelegate) {

    @Provides
    @Singleton
    fun giveContext(): Context = this.application

    @Provides
    @Singleton
    fun givePackageManager(): PackageManager = application.packageManager

    @Provides
    @Singleton
    open fun giveLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            GLog.v("APP LOGG", message)
        })
        interceptor.level = if (GLog.DEBUG_BOOL)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return interceptor
    }

    // Okhttp Client
    @Provides
    open fun giveOkhttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
        return httpClient.build()
    }

    @Provides
    fun giveRetrofitBuilder(): Retrofit.Builder =
        Retrofit.Builder()
            .baseUrl(ConstantsUtil.API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    @Provides
    open fun giveRetrofitService(okHttpClient: OkHttpClient): AppAPIs =
        Retrofit.Builder()
            .baseUrl(ConstantsUtil.API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(AppAPIs::class.java)

    @Provides
    @Named("test")
    open fun givenAppAPIs() : AppAPIs =
        Retrofit.Builder().build().create(AppAPIs::class.java)

    @Provides
    @Singleton
    fun giveGSONInstance(): Gson = Gson()
}

@Module
open class RepositoryModule {
    @Provides
    open fun giveGPRepo(): AppRepository = AppRepository()
}