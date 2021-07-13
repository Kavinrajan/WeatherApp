package com.android.myweatherapp.utils

import org.mockito.ArgumentCaptor
import org.mockito.Mockito.mock

inline fun <reified T: Any> reifiedMock(): T = mock(T::class.java)

fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()