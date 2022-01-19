package before.forget.data.remote.api

import before.forget.data.remote.response.ReportLabelingData
import before.forget.data.remote.response.ResponseWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface StatisticService {
    @GET("statistic/first/{date}")
    fun requestReportLabelingData(
        @Header("accesstoken") token: String,
        @Path("date") date: String,
    ): Call<ResponseWrapper<ReportLabelingData>>
}