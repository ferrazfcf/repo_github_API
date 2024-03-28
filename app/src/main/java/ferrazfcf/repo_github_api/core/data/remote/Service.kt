package ferrazfcf.repo_github_api.core.data.remote

import ferrazfcf.repo_github_api.users_list_and_search.data.user_item.UserItemDTO
import ferrazfcf.repo_github_api.users_list_and_search.data.user_item.UserSearchDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("users")
    suspend fun getUsers(): Response<List<UserItemDTO>>

    @GET("search/users")
    suspend fun searchUsersByName(
        @Query("q") userId: String
    ): Response<UserSearchDTO>
}
