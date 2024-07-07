package io.github.shvmsaini.nextdns.service

import io.github.shvmsaini.nextdns.BuildConfig
import io.github.shvmsaini.nextdns.service.model.DenylistModel
import io.github.shvmsaini.nextdns.service.model.LogsModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Streaming

interface ApiService {

    @GET("profiles/{id}/denylist?lang=en")
    suspend fun getDenylist(@Path("id") id: String) : DenylistModel

    @GET("profiles/{id}/allowlist?lang=en")
    suspend fun getAllowlist(@Path("id") id: String) : DenylistModel

    @POST("profiles/{id}/allowlist")
    suspend fun postAllowlist(@Path("id") id: String, @Body allowlist: DenylistModel.Data)

    @POST("profiles/{id}/denylist")
    suspend fun postDenylist(@Path("id") id: String, @Body denylist: DenylistModel.Data)

    @GET("profiles/{id}/logs?lang=en")
    suspend fun getLogs(@Path("id") id: String): LogsModel

    @Streaming
    @GET("profiles/{id}/logs/stream?lang=en")
    suspend fun getLogsStream(@Path("id") id: String): LogsModel

    companion object {
        private const val BASE_URL = "https://api.nextdns.io/"

        fun create(): ApiService {
            val interceptor = Interceptor { chain ->
                val request =
                    chain.request().newBuilder().addHeader("X-Api-Key", BuildConfig.API_KEY).build()
                chain.proceed(request)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}