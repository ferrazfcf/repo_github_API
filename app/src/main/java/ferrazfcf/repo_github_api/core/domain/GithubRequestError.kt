package ferrazfcf.repo_github_api.core.domain

import androidx.annotation.StringRes
import ferrazfcf.repo_github_api.R

enum class GithubRequestError(@StringRes val message: Int) {
    SERVICE_UNAVAILABLE(R.string.error_service_unavailable),
    SERVER_ERROR(R.string.server_error),
    CLIENT_ERROR(R.string.client_error),
    UNKNOWN_ERROR(R.string.unknown_error)
}

class GithubRequestException(
    val error: GithubRequestError,
    throwable: Throwable? = null
) : Throwable(
    message = "An error occurred: $error",
    cause = throwable
)
