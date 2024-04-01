package ferrazfcf.repo_github_api.core.util.data

sealed interface Resource<out T> {
    data class Success<T>(val data: T): Resource<T>
    @JvmInline
    value class Error(val throwable: Throwable): Resource<Nothing>
}
