package ferrazfcf.repo_github_api.user_details_and_repos.domain.user_info

import android.util.Log
import ferrazfcf.repo_github_api.user_details_and_repos.data.user_info.UserInfoDTO
import ferrazfcf.repo_github_api.users_list_and_search.data.user_item.UserItemDTO

fun UserInfoDTO.toUserInfo(): UserInfo? {
    return runCatching {
        requireNotNull(login) { "User login should not be null" }
        requireNotNull(pageUrl) { "User page url should not be null" }
        UserInfo(
            login = login,
            avatar = avatar,
            pageUrl = pageUrl,
            name = name,
            company = company,
            blog = blog,
            location = location,
            email = email,
            bio = bio
        )
    }.onFailure {
        Log.e("MAPPER_ERROR", "UserInfo", it)
    }.getOrNull()
}
