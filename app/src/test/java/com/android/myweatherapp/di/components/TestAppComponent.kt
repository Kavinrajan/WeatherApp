package com.android.myweatherapp.di.components

import com.android.myweatherapp.di.modules.ApplicationModule
import com.android.myweatherapp.di.modules.RepositoryModule
import com.android.myweatherapp.mockk_samples.AppRepositoryInjectNamedTest
import com.android.myweatherapp.mockk_samples.AppRepositoryInjectTest
import com.android.myweatherapp.mockk_samples.BaseTest
import com.android.myweatherapp.repository.AppRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (ApplicationModule::class),
    (RepositoryModule::class)
])
interface TestAppComponent : ApplicationComponent {
    fun into(appRepository: AppRepositoryInjectNamedTest)
    fun into(appRepository: AppRepositoryInjectTest)
    fun into(baseTest: BaseTest)
}