package ferrazfcf.repo_github_api.users_list_and_search.domain.user_item

import android.util.Log
import ferrazfcf.repo_github_api.users_list_and_search.data.user_item.UserItemDTO

fun UserItemDTO.toUserItem(): UserItem? {
    return runCatching {
        requireNotNull(name) { "User name should not be null" }
        UserItem(
            name = name,
            avatar = avatar
        )
    }.onFailure {
        Log.e("PARSER_ERROR", "UserItem", it)
    }.getOrNull()
}
