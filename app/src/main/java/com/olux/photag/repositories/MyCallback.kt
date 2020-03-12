package com.olux.photag.repositories

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyCallback<T>(val onSuccessCallback: (T) -> Unit) : Callback<T> {
    override fun onResponse(call: Call<T>, response: Response<T>) {
        Log.d("test", "onResponse: $response")

        if (response.isSuccessful && response.body() != null && response.code() in 200 until 400) {
            onSuccessCallback(response.body()!!)
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        Log.d("test", "onFailure: ${t.message}")
    }
}
