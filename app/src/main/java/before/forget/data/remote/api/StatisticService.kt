package before.forget.data.remote.api

import ResponseRankingData
import before.forget.data.remote.response.ResponseGraphData
import before.forget.data.remote.response.ResponseLabelingData
import before.forget.data.remote.response.ResponseWrapper
import before.forget.data.remote.tempToken
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface StatisticService {
    @GET("statistic/first/{date}")
    fun responseLabelingData(
        @Header("accesstoken") token: String? = tempToken,
        @Path("date") date: String
    ): Call<ResponseWrapper<ResponseLabelingData>>

    @GET("statistic/second/{date}/{count}")
    fun responseGraphData(
        @Header("accesstoken") token: String? = tempToken,
        @Path("date") date: String,
        @Path("count") count: Int
    ): Call<ResponseWrapper<ResponseGraphData>>

    @GET("statistic/third/{date}")
    fun responseRankingData(
        @Header("accesstoken") token: String? = tempToken,
        @Path("date") date: String
    ): Call<ResponseWrapper<ResponseRankingData>>
}