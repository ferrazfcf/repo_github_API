package ferrazfcf.repo_github_api.users_list_and_search.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import ferrazfcf.repo_github_api.R
import ferrazfcf.repo_github_api.core.theme.LightBlue
import ferrazfcf.repo_github_api.core.theme.RepoGithubTheme
import ferrazfcf.repo_github_api.users_list_and_search.presentation.component.UserListItem
import ferrazfcf.repo_github_api.users_list_and_search.presentation.preview.UsersListAndSearchParams

@Composable
fun UsersListAndSearch(
    state: UsersListAndSearchState,
    onEvent: (UsersListAndSearchEvent) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val error = state.error?.let { stringResource(id = it) }

    LaunchedEffect(error) {
        error?.let { error ->
            snackbarHostState.showSnackbar(
                message = error,
                actionLabel = "Retry",
                duration = SnackbarDuration.Indefinite
            ).also { result ->
                when(result) {
                    SnackbarResult.ActionPerformed -> {
                        if (state.performedSearch) {
                            onEvent(UsersListAndSearchEvent.SearchUsersByName)
                        } else {
                            onEvent(UsersListAndSearchEvent.LoadUsers)
                        }
                    }

                    SnackbarResult.Dismissed -> onEvent(UsersListAndSearchEvent.OnErrorSeen)
                }
            }
            onEvent(UsersListAndSearchEvent.OnErrorSeen)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            SearchBar(state, onEvent)
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
                if(state.isLoading) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(80.dp),
                            color = MaterialTheme.colorScheme.primary,
                            strokeWidth = 8.dp
                        )
                    }
                }
            }

            itemsIndexed(
                items = state.users,
                key = { index, item ->
                    item.hashCode().plus(index)
                }
            ) { _, item ->
                UserListItem(
                    modifier = Modifier.fillMaxWidth(),
                    userItem = item
                ) { user ->
                    onEvent(UsersListAndSearchEvent.ShowUserDetailsAndRepos(user))
                }
            }
        }
    }
}

@Composable
private fun SearchBar(
    state: UsersListAndSearchState,
    onEvent: (UsersListAndSearchEvent) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.searchName,
            textStyle = MaterialTheme.typography.bodyLarge,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_user_placeholder),
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightBlue
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        keyboardController?.hide()
                        onEvent(UsersListAndSearchEvent.SearchUsersByName)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(id = R.string.search),
                        tint = LightBlue
                    )
                }
            },
            singleLine = true,
            onValueChange = { name ->
                onEvent(UsersListAndSearchEvent.ChangeSearchText(name))
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.users_list_header),
                style = MaterialTheme.typography.headlineLarge,
                color = LightBlue,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (state.performedSearch) {
                Button(
                    onClick = {
                        onEvent(UsersListAndSearchEvent.ResetUsers)
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.reset),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun UsersListAndSearchPreview(
    @PreviewParameter(UsersListAndSearchParams::class)
    state: UsersListAndSearchState
) {
    RepoGithubTheme {
        UsersListAndSearch(
            state = state,
            onEvent = { }
        )
    }
}
