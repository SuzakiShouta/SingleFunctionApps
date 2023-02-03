package com.example.letslavlusapi

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.http.GET
import java.lang.reflect.Type

interface LavlusApi {
    @GET("/projects")
    fun getProjects(): Call<String>

    companion object {
        operator fun invoke(
            authInterceptor: Interceptor
        ): LavlusApi {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://lavlus-api.ayaka.work")
                .addConverterFactory(StringConverterFactory())
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(authInterceptor)
                        .build()
                )
                .build()

            return retrofit.create(LavlusApi::class.java)
        }
    }
}

class StringConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return if (type == String::class.java) {
            Converter<ResponseBody, String> { value -> value.string() }
        } else {
            null
        }
    }
}