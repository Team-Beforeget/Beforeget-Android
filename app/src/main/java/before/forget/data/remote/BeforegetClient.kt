package before.forget.data.remote

import before.forget.data.remote.api.AuthService
import before.forget.data.remote.api.CategoryService
import before.forget.data.remote.api.MainService
import before.forget.data.remote.api.PostService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BeforegetClient {
    private const val BASE_URL = "https://asia-northeast3-appjam-beforeget.cloudfunctions.net/api/"

    val authService: AuthService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        provideService(AuthService::class.java)
    }

    val postService: PostService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        provideService(PostService::class.java)
    }

    val mainService: MainService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        provideService(MainService::class.java)
    }

    val categoryService: CategoryService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        provideService(CategoryService::class.java)
    }

    private fun <T> provideService(clazz: Class<T>): T = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideHttpLoggingClient())
        .build()
        .create(clazz)

    private fun provideHttpLoggingClient(): OkHttpClient =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }.let {
            OkHttpClient.Builder().addInterceptor(it).build()
        }
}
