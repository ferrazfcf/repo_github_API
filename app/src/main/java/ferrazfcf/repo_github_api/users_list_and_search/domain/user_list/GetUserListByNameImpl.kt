package ferrazfcf.repo_github_api.users_list_and_search.domain.user_list

import ferrazfcf.repo_github_api.core.util.data.Resource
import ferrazfcf.repo_github_api.users_list_and_search.domain.repository.UserItemRepository
import ferrazfcf.repo_github_api.users_list_and_search.domain.user_item.UserItem
import ferrazfcf.repo_github_api.users_list_and_search.domain.user_item.toUserItem
import kotlinx.coroutines.ensureActive
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class GetUserListByNameImpl @Inject constructor(
    private val repository: UserItemRepository
) : GetUserListByName {

    override suspend fun invoke(login: String): Resource<List<UserItem>> {
        return repository.runCatching {
            val users = searchUserByName(login).mapNotNull { dto ->
                dto.toUserItem()
            }
            coroutineContext.ensureActive()
            Resource.Success(users)
        }.getOrElse { error ->
            error.printStackTrace()
            Resource.Error(error)
        }
    }
}
