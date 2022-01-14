/*
package before.foreget.util

import before.foreget.data.remote.response.ResponseWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> Call<ResponseWrapper<T>>.enqueueRequestCallback() {
    val callback = object : Callback<ResponseWrapper<T>> {
        override fun onResponse(
            call: Call<ResponseWrapper<T>>,
            response: Response<ResponseWrapper<T>>
        ) {
            TODO("Not yet implemented")
        }

        override fun onFailure(call: Call<ResponseWrapper<T>>, t: Throwable) {
            TODO("Not yet implemented")
        }

    }
    this.enqueue()
}

class RequestCallback<T>(
    val callback: (ResponseWrapper<T>) -> Unit
):Callback<ResponseWrapper<T>> {
    override fun onResponse(
        call: Call<ResponseWrapper<T>>,
        response: Response<ResponseWrapper<T>>
    ) {
        TODO("Not yet implemented")
    }

    override fun onFailure(call: Call<ResponseWrapper<T>>, t: Throwable) {
        TODO("Not yet implemented")
    }

}*/
