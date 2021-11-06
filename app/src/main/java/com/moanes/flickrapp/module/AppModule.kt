package com.moanes.flickrapp.module

import com.moanes.flickrapp.BuildConfig
import com.moanes.flickrapp.data.network.Service
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val appModules = module {
    // The Retrofit service using our custom HTTP client instance as a singleton
    single {
        createWebService<Service>(
            okHttpClient = createHttpClient(),
            baseUrl = BuildConfig.SERVER_URL
        )
    }

}

inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}

/* Returns a custom OkHttpClient instance with interceptor. Used for building Retrofit service */
fun createHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(requestInterceptor)
        .readTimeout(50, TimeUnit.SECONDS)
        .writeTimeout(50, TimeUnit.SECONDS)
        .connectTimeout(50, TimeUnit.SECONDS)
        .build()
}

private val requestInterceptor: Interceptor = Interceptor { chain ->
    val apiKey = "424932484db0431a64a28cb3f429819d"
    var request = chain.request()
    val requestUrl = request.url

    val url = requestUrl.newBuilder().addQueryParameter("api_key", apiKey).build()

    request = request.newBuilder()
        .url(url)
        .build()

    chain.proceed(request)
}