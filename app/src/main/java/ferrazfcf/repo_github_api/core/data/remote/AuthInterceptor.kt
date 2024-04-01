package ferrazfcf.repo_github_api.core.data.remote

import ferrazfcf.repo_github_api.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.request()
            .newBuilder()
            .addAuthorizationTokenHeader()
            .build()
            .let(chain::proceed)
    }

    private fun Request.Builder.addAuthorizationTokenHeader(): Request.Builder {

        return BuildConfig.ACCESS_TOKEN.ifBlank { null }?.let { token ->
            addHeader("Authorization", token)
        } ?: this
    }
}
