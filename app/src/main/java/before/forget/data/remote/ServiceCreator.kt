package before.forget.data.remote

import before.forget.data.remote.api.MyRecordAllService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = "https://asia-northeast3-appjam-beforeget.cloudfunctions.net/api"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val myRecordAllService: MyRecordAllService = retrofit.create(MyRecordAllService::class.java)
}
