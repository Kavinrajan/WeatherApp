package com.android.myweatherapp.data.remote

import com.android.myweatherapp.data.model.api.response.APIResponse
import com.android.myweatherapp.util.ConstantsUtil
import io.reactivex.Flowable
import io.reactivex.internal.operators.flowable.FlowableAll
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface AppAPIs {

    @GET(
        ConstantsUtil.FORWARD_SLASH + ConstantsUtil.API_ENDPOINT
    )  fun getForecastTemp(
        @Query(ConstantsUtil.KEY_PARAM)
        apiKey: String = ConstantsUtil.API_KEY,
        @Query(ConstantsUtil.QUERY_PARAM)
        latlng: String? = null,
        @Query(ConstantsUtil.DAYS_PARAM)
        days: String = ConstantsUtil.FOUR
        ): Flowable<APIResponse>

    @GET(
        ConstantsUtil.FORWARD_SLASH + ConstantsUtil.API_ENDPOINT
    )  fun getForecastTempByCity(
        @Query(ConstantsUtil.KEY_PARAM)
        apiKey: String = ConstantsUtil.API_KEY,
        @Query(ConstantsUtil.QUERY_PARAM)
        city: String? = null
    ): Flowable<APIResponse>

    @GET
    fun getForecastTempWithUrl(
        @Url
        url: String): Flowable<APIResponse>

}