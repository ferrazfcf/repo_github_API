package ferrazfcf.repo_github_api.user_details_and_repos.presentation.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ferrazfcf.repo_github_api.R
import ferrazfcf.repo_github_api.core.presentation.component.UrlText
import ferrazfcf.repo_github_api.core.theme.RepoGithubTheme
import ferrazfcf.repo_github_api.user_details_and_repos.domain.repo_item.RepoItem
import kotlinx.datetime.Clock

@Composable
fun RepoListItem(
    modifier: Modifier = Modifier,
    item: RepoItem
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    AnimatedContent(
        targetState = isExpanded,
        label = "Translate Text Field"
    ) { expanded ->
        if (expanded) {
            Expanded(modifier, item) {
                isExpanded = isExpanded.not()
            }
        } else {
            Collapsed(modifier, item) {
                isExpanded = isExpanded.not()
            }
        }
    }

}

@Composable
private fun Expanded(
    modifier: Modifier,
    item: RepoItem,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 5.dp, shape = MaterialTheme.shapes.large)
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.surface)
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            HorizontalDivider(
                color = MaterialTheme.colorScheme.onSurface
            )
            item.description?.let { description ->
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
            UrlText(
                url = item.pageUrl,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.created_at, item.formattedCreatedAt),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                item.formattedUpdatedAt?.let { date ->
                    Text(
                        text = stringResource(id = R.string.updated_at, date),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
private fun Collapsed(
    modifier: Modifier,
    item: RepoItem,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 5.dp, shape = MaterialTheme.shapes.large)
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.primary)
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Text(
            text = item.name,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun RepoListItemPreview() {
    RepoGithubTheme {
        val now = Clock.System.now()
        val repoItem = RepoItem(
            name = "nice-repo",
            pageUrl = "https://github.com/ferrazfcf",
            description = "this is a nice repo, " +
                    "created to study some cool stuff about android native development",
            createdAt = now,
            updatedAt = now
        )
        RepoListItem(item = repoItem)
    }
}
