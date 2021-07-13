package com.android.myweatherapp.di.modules

import com.android.myweatherapp.GDelegate
import com.android.myweatherapp.data.remote.AppAPIs
import com.android.myweatherapp.repository.AppRepository
import io.mockk.mockk
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class TestApplicationModule(delegate: GDelegate) : ApplicationModule(delegate) {
    override fun giveLoggingInterceptor(): HttpLoggingInterceptor {
        return mockk()
    }

    override fun giveOkhttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return mockk()
    }

    override fun giveRetrofitService(okHttpClient: OkHttpClient): AppAPIs {
        return mockk()
    }

    override fun givenAppAPIs(): AppAPIs {
        return mockk()
    }

}

class TestRepositoryModule(): RepositoryModule() {
    override fun giveGPRepo(): AppRepository {
        return mockk()
    }
}