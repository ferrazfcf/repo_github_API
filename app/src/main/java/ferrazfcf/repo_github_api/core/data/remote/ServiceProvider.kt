package ferrazfcf.repo_github_api.core.data.remote

import ferrazfcf.repo_github_api.di.qualifier.BaseUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class ServiceProvider @Inject constructor(
    private val httpClient: OkHttpClient,
    private val converterFactory: MoshiConverterFactory,
    @BaseUrl private val baseUrl: String
) {
    private fun retrofitBuilder(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    fun provideService(): Service {
        return retrofitBuilder().create(Service::class.java)
    }
}
