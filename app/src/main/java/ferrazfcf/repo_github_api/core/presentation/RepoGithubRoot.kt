package ferrazfcf.repo_github_api.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ferrazfcf.repo_github_api.core.util.lifecycle.ExecuteOnLifecycleEvent
import ferrazfcf.repo_github_api.user_details_and_repos.presentation.UserDetailsAndRepos
import ferrazfcf.repo_github_api.user_details_and_repos.presentation.UserDetailsAndReposEvent
import ferrazfcf.repo_github_api.user_details_and_repos.presentation.UserDetailsAndReposViewModel
import ferrazfcf.repo_github_api.users_list_and_search.presentation.UsersListAndSearch
import ferrazfcf.repo_github_api.users_list_and_search.presentation.UsersListAndSearchEvent
import ferrazfcf.repo_github_api.users_list_and_search.presentation.UsersListAndSearchViewModel

@Composable
fun RepoGithubRoot() {
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
                        route = Routes.USER_DETAILS_AND_REPOS + "/${event.login}",
                    )
                    else -> viewModel.onEvent(event)
                }
            }
        }

        composable(
            route = Routes.USER_DETAILS_AND_REPOS + "/{login}",
            arguments = listOf(
                navArgument("login") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val login = backStackEntry.arguments?.getString("login")

            if (login == null) {
                navController.popBackStack()
                return@composable
            }

            val viewModel = hiltViewModel<UserDetailsAndReposViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            LocalLifecycleOwner.current.lifecycle.ExecuteOnLifecycleEvent(Lifecycle.Event.ON_RESUME) {
                viewModel.onEvent(UserDetailsAndReposEvent.SetUserLogin(login))
            }

            UserDetailsAndRepos(state = state) { event ->
                when(event) {
                    is UserDetailsAndReposEvent.NavigateBack -> navController.popBackStack()
                    else -> viewModel.onEvent(event)
                }
            }
        }
    }
}
