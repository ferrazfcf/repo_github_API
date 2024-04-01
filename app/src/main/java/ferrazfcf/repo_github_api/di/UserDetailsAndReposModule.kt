package ferrazfcf.repo_github_api.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ferrazfcf.repo_github_api.user_details_and_repos.domain.repo_list.GetRepoList
import ferrazfcf.repo_github_api.user_details_and_repos.domain.repo_list.GetRepoListImpl
import ferrazfcf.repo_github_api.user_details_and_repos.domain.user_info.GetUserInfo
import ferrazfcf.repo_github_api.user_details_and_repos.domain.user_info.GetUserInfoImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UserDetailsAndReposModule {

    @Binds
    @ViewModelScoped
    fun bindGetUserInfo(getUserInfo: GetUserInfoImpl): GetUserInfo

    @Binds
    @ViewModelScoped
    fun bindGetRepoList(getRepoListImpl: GetRepoListImpl): GetRepoList
}
