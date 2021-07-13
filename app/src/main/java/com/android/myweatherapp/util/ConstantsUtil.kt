package com.android.myweatherapp.util

import java.text.SimpleDateFormat
import java.util.*

object ConstantsUtil {
    const val DASH = "---"
    const val HYPHEN = "-"
    const val EMPTY = ""
    const val INVALID_INPUT = "invalid input"
    const val TESTING = "Testing"

    const val IAM_SURE = "IAM_SURE"

    const val DEGREE_CIRCLE = "\u00B0"

    const val BLANK_SPACE = " "
    const val PERM_FINE_LOCATION_REQUEST_CODE = 9090
    const val PERM_COARSE_LOCATION_REQUEST_CODE = 8787

    const val COMMA = ","

    const val IS_LAUNCHED_FROM_TEST = "isLaunchedFromTest"

    const val FORWARD_SLASH = "/"
    //const val API_ENDPOINT = "https://api.apixu.com"
    const val API_ENDPOINT = "http://api.weatherstack.com"

    const val FORECAST_TEMP_API = "forecast.json"
    const val FORECAST_PATH = "current"

    const val API_VERSION = "v1"

    const val KEY_PARAM = "access_key"
    const val QUERY_PARAM = "query"
    const val DAYS_PARAM = "forecast_days"

    const val API_KEY = "f94ccbd25a8ede13f152c714d671067f"

    const val MOCK_URL = "MockURL"
    const val FOUR = "4"

    private val dateFormat by lazy {
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    }

    private val dayFormat by lazy {
        SimpleDateFormat("EEEE", Locale.getDefault())
    }

    //  input string -> 2019-06-04
    fun findDayOfDate(dateString: String): String =
        if (!dateString.contains(HYPHEN))
            EMPTY
        else
            dayFormat.format(dateFormat.parse(dateString))
}