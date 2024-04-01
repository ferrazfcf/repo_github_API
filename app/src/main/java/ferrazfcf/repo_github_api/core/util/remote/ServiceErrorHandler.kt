package ferrazfcf.repo_github_api.core.util.remote

import ferrazfcf.repo_github_api.core.domain.GithubRequestError
import ferrazfcf.repo_github_api.core.domain.GithubRequestException
import okio.IOException
import retrofit2.Response

suspend fun <T> resultOrThrow(request: suspend () -> Response<T>) : T {
    val result = try {
        request()
    } catch (e: IOException) {
        throw GithubRequestException(
            error = GithubRequestError.SERVICE_UNAVAILABLE,
            throwable = e
        )
    }

    when (result.code()) {
        in 200..299 -> Unit
        in 400..499 -> throw GithubRequestException(GithubRequestError.CLIENT_ERROR)
        500 -> throw GithubRequestException(GithubRequestError.SERVER_ERROR)
        else -> throw GithubRequestException(GithubRequestError.UNKNOWN_ERROR)
    }

    return try {
        result.body()!!
    } catch (e: Exception) {
        throw GithubRequestException(GithubRequestError.SERVER_ERROR)
    }
}
