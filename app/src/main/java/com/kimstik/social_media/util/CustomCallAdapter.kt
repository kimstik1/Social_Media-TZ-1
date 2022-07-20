package com.kimstik.social_media.util

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.Type


class CustomCallAdapter<T>(returnType: Type): CallAdapter<T, T> {
    private val returnType: Type
    override fun responseType(): Type {
        return returnType
    }

    override fun adapt(call: Call<T>): T? {
        try {
            return call.execute().body()
        } catch(e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    class Factory: CallAdapter.Factory() {
        override fun get(returnType: Type, annotations: Array<Annotation?>?, retrofit: Retrofit?): CallAdapter<*, *> {
            return CustomCallAdapter<Any?>(returnType)
        }
    }

    init {
        this.returnType = returnType
    }
}