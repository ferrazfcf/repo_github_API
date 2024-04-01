package ferrazfcf.repo_github_api.user_details_and_repos.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ferrazfcf.repo_github_api.R
import ferrazfcf.repo_github_api.core.presentation.component.BasicTopBar
import ferrazfcf.repo_github_api.core.presentation.component.ErrorRetry
import ferrazfcf.repo_github_api.core.presentation.component.UrlText
import ferrazfcf.repo_github_api.core.theme.RepoGithubTheme
import ferrazfcf.repo_github_api.user_details_and_repos.domain.user_info.UserInfo
import ferrazfcf.repo_github_api.user_details_and_repos.presentation.component.RepoListItem
import ferrazfcf.repo_github_api.user_details_and_repos.presentation.preview.UserDetailsAndReposParams

@Composable
fun UserDetailsAndRepos(
    state: UserDetailsAndReposState,
    onEvent: (UserDetailsAndReposEvent) -> Unit
) {
    Scaffold(
        topBar = {
            BasicTopBar(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(8.dp),
                text = "${state.userLogin}"
            ) {
                onEvent(UserDetailsAndReposEvent.NavigateBack)
            }
        }
    ) { innerPadding ->
        val layoutDirection = LocalLayoutDirection.current
        val columnPadding = PaddingValues(
            top = innerPadding.calculateTopPadding() + 4.dp,
            bottom = innerPadding.calculateBottomPadding() + 16.dp,
            start = innerPadding.calculateStartPadding(layoutDirection) + 16.dp,
            end = innerPadding.calculateEndPadding(layoutDirection) + 16.dp,
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = columnPadding,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                UserDetails(
                    state = state,
                    onEvent = onEvent
                )
            }

            item {
                with(state) {
                    when {
                        isRepoListLoading && isUserInfoLoading.not() -> Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(80.dp),
                                color = MaterialTheme.colorScheme.primary,
                                trackColor = MaterialTheme.colorScheme.onSurface,
                                strokeWidth = 8.dp
                            )
                        }
                        repoListError != null -> ErrorRetry(
                            error = stringResource(id = repoListError),
                            color = MaterialTheme.colorScheme.onBackground
                        ) {
                            onEvent(UserDetailsAndReposEvent.LoadRepoList)
                        }
                    }
                }
            }

            itemsIndexed(
                items = state.repoList,
                key = { index, item ->
                    item.hashCode().plus(index)
                }
            ) { _, item ->
                RepoListItem(item = item)
            }
        }
    }
}

@Composable
private fun UserDetails(
    state: UserDetailsAndReposState,
    onEvent: (UserDetailsAndReposEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        with(state) {
            when {
                isUserInfoLoading -> Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(80.dp),
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = MaterialTheme.colorScheme.onSurface,
                        strokeWidth = 8.dp
                    )
                }
                userInfoError != null -> ErrorRetry(
                    error = stringResource(id = userInfoError),
                    color = MaterialTheme.colorScheme.onSurface
                ) {
                    onEvent(UserDetailsAndReposEvent.LoadUserInfo)
                }
                userInfo != null -> UserDetailsLoaded(userInfo)
            }
        }
    }
}

@Composable
private fun UserDetailsLoaded(userInfo: UserInfo) {
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            model = userInfo.avatar,
            contentDescription = stringResource(
                id = R.string.user_image_description,
                userInfo.login
            )
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = userInfo.name ?: userInfo.login,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
    HorizontalDivider(
        color = MaterialTheme.colorScheme.onSurface
    )
    userInfo.email?.let { email ->
        Text(
            modifier = Modifier.clickable {
                clipboardManager.setText(
                    buildAnnotatedString { append(email) }
                )
                Toast.makeText(
                    context,
                    R.string.copied_to_clipboard,
                    Toast.LENGTH_LONG
                ).show()
            },
            text = email,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
    userInfo.location?.let { location ->
        Text(
            text = location,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
    userInfo.company?.let { company ->
        Text(
            text = company,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
    userInfo.bio?.let { bio ->
        Text(
            text = stringResource(R.string.bio, bio),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
    UrlText(
        url = userInfo.pageUrl,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface
    )
    userInfo.blog?.let { blog ->
        UrlText(
            url = blog,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview
@Composable
private fun UserDetailsAndReposPreview(
    @PreviewParameter(UserDetailsAndReposParams::class)
    state: UserDetailsAndReposState
) {
    RepoGithubTheme {
        UserDetailsAndRepos(
            state = state,
            onEvent = { }
        )
    }
}
