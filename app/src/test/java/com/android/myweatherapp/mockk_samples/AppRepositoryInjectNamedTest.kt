package com.android.myweatherapp.mockk_samples

import com.android.myweatherapp.GDelegate
import com.android.myweatherapp.data.model.api.response.APIResponse
import com.android.myweatherapp.data.remote.AppAPIs
import com.android.myweatherapp.di.components.DaggerTestAppComponent
import com.android.myweatherapp.di.modules.TestApplicationModule
import com.android.myweatherapp.di.modules.TestRepositoryModule
import io.mockk.every
import io.reactivex.Flowable
import junit.framework.Assert.assertNotNull
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

class AppRepositoryInjectNamedTest {

    @Inject
    @Named("test")
    lateinit var appAPIs :AppAPIs

    @Before
    fun setUp() {
        val component = DaggerTestAppComponent.builder()
                .applicationModule(TestApplicationModule(GDelegate()))
                .repositoryModule(TestRepositoryModule())
                .build()
        component.into(this)
    }

    @Test
    fun `myTest`() {
        assertNotNull(appAPIs)
        every { appAPIs.getForecastTempWithUrl("r") } returns Flowable.just(APIResponse())
        val result = appAPIs.getForecastTempWithUrl("r")
        result.test().assertValue(APIResponse())
    }

}