package before.forget.data.remote.api

import before.forget.data.remote.request.RequestPost
import before.forget.data.remote.response.PostResponseData
import before.forget.data.remote.response.ResponseMyRecordAll
import before.forget.data.remote.response.ResponseWrapper
import before.forget.data.remote.tempToken
import retrofit2.Call
import retrofit2.http.*

interface PostService {
    @GET("post")
    fun getMyrecordAllData(
        @Header("accesstoken") token: String? = tempToken
    ): Call<ResponseWrapper<List<ResponseMyRecordAll>>>

    @GET("post/filter")
    fun getMyRecordFilterData(
        @Header("accesstoken") token: String? = tempToken,
        @Query("date") date: String,
        @Query("media") media: String,
        @Query("star") star: String
    ): Call<ResponseWrapper<List<ResponseMyRecordAll>>>

    @POST("post/upload")
    fun postUpload(
        @Header("accesstoken") token: String? = tempToken,
        @Body body: RequestPost
    ): Call<PostResponseData>
}
