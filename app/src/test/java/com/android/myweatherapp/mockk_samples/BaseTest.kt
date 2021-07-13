package com.android.myweatherapp.mockk_samples

import com.android.myweatherapp.GDelegate
import com.android.myweatherapp.data.remote.AppAPIs
import com.android.myweatherapp.di.components.DaggerTestAppComponent
import com.android.myweatherapp.di.modules.TestApplicationModule
import com.android.myweatherapp.di.modules.TestRepositoryModule
import javax.inject.Inject

open class BaseTest {

    @Inject
    lateinit var appAPIs: AppAPIs

    fun setUp() {
        val component = DaggerTestAppComponent.builder()
                .applicationModule(TestApplicationModule(GDelegate()))
                .repositoryModule(TestRepositoryModule())
                .build()
        component.into(this)
    }
}