package com.android.myweatherapp.di.components

import com.android.myweatherapp.GDelegate
import com.android.myweatherapp.di.modules.ApplicationModule
import com.android.myweatherapp.di.modules.RepositoryModule
import com.android.myweatherapp.repository.BaseRepository
import com.android.myweatherapp.ui.base.BaseViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [(ApplicationModule::class),(RepositoryModule::class)]
)
interface ApplicationComponent {
    fun inject(into : GDelegate)
    fun inject(into : BaseRepository)
    fun inject(into : BaseViewModel)
}