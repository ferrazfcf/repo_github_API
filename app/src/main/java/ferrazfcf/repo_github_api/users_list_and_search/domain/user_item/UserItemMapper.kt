package ferrazfcf.repo_github_api.users_list_and_search.domain.user_item

import android.util.Log
import ferrazfcf.repo_github_api.users_list_and_search.data.user_item.UserItemDTO

fun UserItemDTO.toUserItem(): UserItem? {
    return runCatching {
        requireNotNull(login) { "User login should not be null" }
        UserItem(
            login = login,
            avatar = avatar
        )
    }.onFailure {
        Log.e("MAPPER_ERROR", "UserItem", it)
    }.getOrNull()
}
