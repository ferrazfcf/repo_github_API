package ferrazfcf.repo_github_api.di

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ferrazfcf.repo_github_api.core.data.remote.AuthInterceptor
import ferrazfcf.repo_github_api.core.data.remote.HeadersInterceptor
import ferrazfcf.repo_github_api.core.data.remote.Service
import ferrazfcf.repo_github_api.core.data.remote.ServiceProvider
import ferrazfcf.repo_github_api.di.qualifier.BaseUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideLoggerInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor.Logger { message ->
            Log.d("HTTP_LOGGER", message)
        }
        return HttpLoggingInterceptor(logger).setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideHttpClient(
        headers: HeadersInterceptor,
        logger: HttpLoggingInterceptor,
        auth: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headers)
            .addInterceptor(logger)
            .addInterceptor(auth)
            .build()
    }

    @Provides
    fun provideMoshiConverter(): MoshiConverterFactory {
        val moshi: Moshi = Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return MoshiConverterFactory.create(moshi)
    }

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String {
        return "https://api.github.com"
    }

    @Provides
    @Singleton
    fun provideService(provider: ServiceProvider): Service {
        return provider.provideService()
    }
}
