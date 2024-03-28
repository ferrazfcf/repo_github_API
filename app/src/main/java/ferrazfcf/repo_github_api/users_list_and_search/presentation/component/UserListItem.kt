package ferrazfcf.repo_github_api.users_list_and_search.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ferrazfcf.repo_github_api.R
import ferrazfcf.repo_github_api.RepoGithubTheme
import ferrazfcf.repo_github_api.users_list_and_search.domain.user_item.UserItem

@Composable
fun UserListItem(
    modifier: Modifier = Modifier,
    userItem: UserItem,
    onClick: (String) -> Unit
) {
    Row(
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(8.dp)
            .clickable {
                onClick(userItem.name)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            model = userItem.avatar,
            contentDescription = stringResource(
                id = R.string.user_image_description,
                userItem.name
            )
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = userItem.name,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserListItemPreview() {
    RepoGithubTheme {
        UserListItem(
            userItem = UserItem(
                name = "octocat",
                avatar = "https://github.com/images/error/octocat_happy.gif"
            ),
            onClick = { }
        )
    }
}
