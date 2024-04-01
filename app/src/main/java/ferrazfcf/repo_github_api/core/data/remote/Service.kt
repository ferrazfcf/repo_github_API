package ferrazfcf.repo_github_api.core.data.remote

import ferrazfcf.repo_github_api.user_details_and_repos.data.repo_item.RepoItemDTO
import ferrazfcf.repo_github_api.user_details_and_repos.data.user_info.UserInfoDTO
import ferrazfcf.repo_github_api.users_list_and_search.data.user_item.UserItemDTO
import ferrazfcf.repo_github_api.users_list_and_search.data.user_item.UserSearchDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @GET("users")
    suspend fun getUsers(): Response<List<UserItemDTO>>

    @GET("search/users")
    suspend fun searchUsersByName(
        @Query("q") login: String
    ): Response<UserSearchDTO>

    @GET("users/{login}")
    suspend fun getUserByName(
        @Path("login") login: String
    ): Response<UserInfoDTO>

    @GET("users/{login}/repos")
    suspend fun getUserRepos(
        @Path("login") login: String
    ): Response<List<RepoItemDTO>>
}
