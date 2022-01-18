package before.forget.data.remote.api

import before.forget.data.local.tempToken
import before.forget.data.remote.response.MainResponseData
import before.forget.data.remote.response.ResponseWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface MainService {

    @GET("home")
    fun getMain(@Header("accesstoken") token: String? = tempToken): Call<ResponseWrapper<MainResponseData>>
}
