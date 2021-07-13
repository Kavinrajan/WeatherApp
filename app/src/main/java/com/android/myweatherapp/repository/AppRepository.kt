package com.android.myweatherapp.repository

import com.android.myweatherapp.data.model.api.response.APIResponse
import com.android.myweatherapp.rx.makeFlowableRxConnection
import com.android.myweatherapp.util.CallBack
import com.android.myweatherapp.util.ConstantsUtil
import io.reactivex.disposables.Disposable


class AppRepository : BaseRepository() {

    var apiCallBack: CallBack<APIResponse>? = null

    fun fetchFromServer(url: String) {
        appAPIs.getForecastTempWithUrl(url)
            .makeFlowableRxConnection(this, ConstantsUtil.TESTING)
    }

    fun fetchFromServer(latitude: String, longitude: String): Disposable =
        appAPIs.getForecastTemp(latlng = latitude + ConstantsUtil.COMMA + longitude)
            .makeFlowableRxConnection(this, ConstantsUtil.FORECAST_PATH)

    override fun onSuccess(obj: Any?, tag: String) {
        when (tag) {
            ConstantsUtil.TESTING,
            ConstantsUtil.FORECAST_PATH ->
                (obj as? APIResponse)?.let {
                    apiCallBack?.invoke(it)
                }

            else ->
                Unit
        }
    }

    //  chain network calls
    fun fetchFromServerByCity(cityName: String): Disposable =
        appAPIs.getForecastTempByCity(city = cityName)
            .makeFlowableRxConnection(this, ConstantsUtil.FORECAST_PATH)

}