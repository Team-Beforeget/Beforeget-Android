package before.forget.data.remote.api

import before.forget.data.remote.response.ResponseMyRecordAll
import before.forget.data.remote.response.ResponseWrapper
import before.forget.data.remote.tempToken
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface MyRecordAllService {
    @GET("post")
    fun getMyrordAllData(
        @Header("accesstoken") token: String? = tempToken
    ): Call<ResponseWrapper<List<ResponseMyRecordAll>>>
}
