package before.forget.data.remote.api

import before.forget.data.remote.response.ResponseFilter
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
// import retrofit2.http.Query

interface FilterService {
    @Headers("accesstoken:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiZW1haWwiOiJzdWJpbjA3MjNAYmVmb3JlZ2V0LmNvbSIsIm5pY2siOiLtj6zrppAiLCJpZEZpcmViYXNlIjoiaXNRM1kzVU4xSVlqdmQzMXpsZk5Bd2FHejFtMSIsImlhdCI6MTY0MjQzNTEzMSwiZXhwIjoxNjQzNjQ0NzMxLCJpc3MiOiJjaGFud29vIn0.zIK0c8Gq1f_GcJ_UjkwABWfXQ5UbVSU5M69uEqZhKkc")
    @GET("/post/filter?")
    // @Query("SELECT /post/filter?date=&media=&star=")

    fun getFilterData(): Call<ResponseFilter>
}
