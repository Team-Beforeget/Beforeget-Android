package before.forget.util

import before.forget.data.remote.response.ResponseWrapper
import com.google.common.truth.Truth.assertThat
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.net.HttpURLConnection

class RequestInfoWrapperTest {
    private lateinit var server: MockWebServer
    private lateinit var mockUrl: HttpUrl
    private lateinit var testService: UtilTestService

    private interface UtilTestService {
        @GET("util")
        fun utilTestRequest(): Call<ResponseWrapper<Unit>>
    }

    private val successResponse by lazy {
        MockResponse().apply {
            setResponseCode(HttpURLConnection.HTTP_OK)
            setBody(SUCCESS_BODY)
        }
    }

    private val errorResponse by lazy {
        MockResponse().apply {
            setResponseCode(HttpURLConnection.HTTP_BAD_GATEWAY)
            setBody(BAD_REQUEST_BODY)
        }
    }

    @Before
    fun setUp() {
        server = MockWebServer()
        server.start()

        mockUrl = server.url("test/")
        testService = Retrofit.Builder()
            .baseUrl(mockUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UtilTestService::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun whenResponseSuccess_thenInvokeUtilOnSuccess() {
        // when : 서버에서 성공 응답 값이 오면
        server.enqueue(successResponse)
        // then : 유틸 함수 성공 부분이 작동 한다.
        var isSuccess: Boolean? = null
        testService.utilTestRequest()
            .callback
            .onSuccess { isSuccess = it.success }
            .onError { isSuccess = it.success }
            .enqueue()
        Thread.sleep(100)

        assertThat(isSuccess).isTrue()
    }

    @Test
    fun whenResponseError_thenInvokeUtilOnError() {
        // when : 서버에서 성공 응답 값이 오면
        server.enqueue(errorResponse)
        // then : 유틸 함수 성공 부분이 작동 한다.
        var isSuccess: Boolean? = null
        testService.utilTestRequest()
            .callback
            .onSuccess { isSuccess = it.success }
            .onError { isSuccess = it.success }
            .enqueue()
        Thread.sleep(100)

        assertThat(isSuccess).isFalse()
    }

    companion object {
        private const val SUCCESS_BODY = "{\n" +
            "    \"status\": 200,\n" +
            "    \"success\": true,\n" +
            "    \"message\": \"Success\"\n" +
            "}"
        private const val BAD_REQUEST_BODY = "{\n" +
            "    \"status\": 400,\n" +
            "    \"success\": false,\n" +
            "    \"message\": \"Bad Request\"\n" +
            "}"
    }
}
