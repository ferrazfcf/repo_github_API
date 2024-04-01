package ferrazfcf.repo_github_api.users_list_and_search.data.repository

import ferrazfcf.repo_github_api.core.data.remote.Service
import ferrazfcf.repo_github_api.core.util.remote.resultOrThrow
import ferrazfcf.repo_github_api.users_list_and_search.data.user_item.UserItemDTO
import ferrazfcf.repo_github_api.users_list_and_search.domain.repository.UserItemRepository
import javax.inject.Inject

class UserItemRepositoryImpl @Inject constructor(
    private val service: Service
) : UserItemRepository {

    override suspend fun getUserList(): List<UserItemDTO> {
        return resultOrThrow {
            service.getUsers()
        }
    }

    override suspend fun searchUserByName(login: String): List<UserItemDTO> {
        return resultOrThrow {
            service.searchUsersByName(login)
        }.items
    }
}
