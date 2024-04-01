package ferrazfcf.repo_github_api.users_list_and_search.domain.repository

import ferrazfcf.repo_github_api.users_list_and_search.data.user_item.UserItemDTO

interface UserItemRepository {
    suspend fun getUserList(): List<UserItemDTO>
    suspend fun searchUserByName(login: String): List<UserItemDTO>
}
