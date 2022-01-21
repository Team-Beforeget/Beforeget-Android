package before.forget.data.remote.api

import before.forget.data.remote.request.RequestPost
import before.forget.data.remote.response.ResponseDetail
import before.forget.data.remote.response.ResponseMyRecordAll
import before.forget.data.remote.response.ResponseWrapper
import before.forget.data.remote.tempToken
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

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

    @GET("post/{postId}")
    fun getDetailFilterData(
        @Header("accesstoken") token: String? = tempToken,
        @Path("postId") postId: Int
    ): Call<ResponseWrapper<List<ResponseDetail>>>

    @POST("post/upload")
    fun postUpload(
        @Header("accesstoken") token: String? = tempToken,
        @Body body: RequestPost
    ): Call<RequestPost>
}
