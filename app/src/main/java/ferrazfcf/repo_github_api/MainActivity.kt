package ferrazfcf.repo_github_api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import ferrazfcf.repo_github_api.core.presentation.Routes
import ferrazfcf.repo_github_api.core.theme.LightBlue
import ferrazfcf.repo_github_api.users_list_and_search.presentation.UsersListAndSearch
import ferrazfcf.repo_github_api.users_list_and_search.presentation.UsersListAndSearchEvent
import ferrazfcf.repo_github_api.users_list_and_search.presentation.UsersListAndSearchViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RepoGithubTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RepoGithubRoot()
                }
            }
        }
    }
}

@Composable
private fun RepoGithubRoot() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.USERS_LIST_AND_SEARCH
    ) {
        composable(Routes.USERS_LIST_AND_SEARCH) {
            val viewModel = hiltViewModel<UsersListAndSearchViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            UsersListAndSearch(state) { event ->
                when(event) {
                    is UsersListAndSearchEvent.ShowUserDetailsAndRepos -> navController.navigate(
                        route = Routes.USER_DETAILS_AND_REPOS + "/${event.user}"
                    )
                    else -> viewModel.onEvent(event)
                }
            }
        }

        composable(
            route = Routes.USER_DETAILS_AND_REPOS + "/{user}",
            arguments = listOf(
                navArgument("user") {
                    type = NavType.StringType
                }
            )
        ) {
            val user = it.arguments!!.getString("user")
            Text(
                text = "User details: $user",
                style = MaterialTheme.typography.headlineLarge,
                color = LightBlue
            )
        }
    }
}
