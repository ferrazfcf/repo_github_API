package ferrazfcf.repo_github_api.user_details_and_repos.domain.user_info

import ferrazfcf.repo_github_api.core.domain.GithubRequestError
import ferrazfcf.repo_github_api.core.domain.GithubRequestException
import ferrazfcf.repo_github_api.core.util.data.Resource
import ferrazfcf.repo_github_api.user_details_and_repos.domain.repository.UserInfoRepository
import kotlinx.coroutines.ensureActive
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class GetUserInfoImpl @Inject constructor(
    private val repository: UserInfoRepository
) : GetUserInfo {

    override suspend fun invoke(login: String): Resource<UserInfo> {
        return repository.runCatching {
            val userInfo = getUserInfo(login).toUserInfo()
                ?: throw GithubRequestException(GithubRequestError.SERVER_ERROR)
            coroutineContext.ensureActive()
            Resource.Success(userInfo)
        }.getOrElse { error ->
            error.printStackTrace()
            Resource.Error(error)
        }
    }
}
