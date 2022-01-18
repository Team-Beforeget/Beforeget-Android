package before.forget.data.remote.api

import before.forget.data.local.tempToken
import before.forget.data.remote.response.CategoryResponseData
import before.forget.data.remote.response.ResponseWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface CategoryService {

    @GET("category/:id")
    fun getAddItem(@Header("accesstoken") token: String? = tempToken): Call<ResponseWrapper<CategoryResponseData>>
}
