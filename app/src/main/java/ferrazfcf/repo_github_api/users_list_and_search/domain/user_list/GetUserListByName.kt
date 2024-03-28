package ferrazfcf.repo_github_api.users_list_and_search.domain.user_list

import ferrazfcf.repo_github_api.core.util.Resource
import ferrazfcf.repo_github_api.users_list_and_search.domain.user_item.UserItem

interface GetUserListByName {
    suspend operator fun invoke(name: String): Resource<List<UserItem>>
}
