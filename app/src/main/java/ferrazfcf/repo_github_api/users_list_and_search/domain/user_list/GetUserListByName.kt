package ferrazfcf.repo_github_api.users_list_and_search.domain.user_list

import ferrazfcf.repo_github_api.core.util.data.Resource
import ferrazfcf.repo_github_api.users_list_and_search.domain.user_item.UserItem

interface GetUserListByName {
    suspend operator fun invoke(login: String): Resource<List<UserItem>>
}
