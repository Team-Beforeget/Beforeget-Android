package before.forget.util

import android.text.TextUtils
import android.util.Log
import before.forget.data.remote.response.ResponseWrapper
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val <T> Call<ResponseWrapper<T>>.callback: RequestInfoWrapper<T>
    get() = RequestInfoWrapper<T>(this)

class RequestInfoWrapper<T>(private val call: Call<ResponseWrapper<T>>) {
    private var onSuccess: ((ResponseWrapper<T>) -> Unit)? = null
    private var onError: ((ResponseWrapper<Unit>) -> Unit)? = null
    private var onCallFailure: (() -> Unit)? = null

    fun enqueue() {
        call.enqueue(ResponseCallback<T>(onSuccess, onError, onCallFailure))
    }

    fun onSuccess(action: (ResponseWrapper<T>) -> Unit): RequestInfoWrapper<T> {
        this.onSuccess = action
        return this
    }

    fun onError(action: (ResponseWrapper<Unit>) -> Unit): RequestInfoWrapper<T> {
        this.onError = action
        return this
    }

    fun onCallFailure(action: () -> Unit): RequestInfoWrapper<T> {
        this.onCallFailure = action
        return this
    }
}

class ResponseCallback<T>(
    private val onSuccess: ((ResponseWrapper<T>) -> Unit)?,
    private val onError: ((ResponseWrapper<Unit>) -> Unit)?,
    private val onCallFailure: (() -> Unit)?
) : Callback<ResponseWrapper<T>> {
    override fun onResponse(
        call: Call<ResponseWrapper<T>>,
        response: Response<ResponseWrapper<T>>
    ) {
        if (response.isSuccessful) {
            onSuccess?.invoke(response.body() ?: return)
            return
        }
        val errorBody = response.errorBody()?.string() ?: return
        val errorResponse = createErrorResponse(errorBody)
        onError?.invoke(errorResponse)
    }

    private fun createErrorResponse(errorBody: String): ResponseWrapper<Unit> {
        val responseType = object : TypeToken<ResponseWrapper<Unit>>() {}.type
        return GsonBuilder().create()
            .fromJson(errorBody, responseType)
    }

    override fun onFailure(call: Call<ResponseWrapper<T>>, t: Throwable) {
        loggingErrorMessage(
            "Call OnFailure Error\n",
            "${t.message}\n",
            "${t.localizedMessage}\n",
            TextUtils.join("\n", t.stackTrace)
        )
        onCallFailure?.invoke()
    }

    private fun loggingErrorMessage(vararg message: String) {
        message.forEach { Log.d(ERROR_TAG, it) }
    }

    companion object {
        private const val ERROR_TAG = "Response Fail"
    }
}
