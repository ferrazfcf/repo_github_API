package ferrazfcf.repo_github_api.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ferrazfcf.repo_github_api.users_list_and_search.domain.user_list.GetUserList
import ferrazfcf.repo_github_api.users_list_and_search.domain.user_list.GetUserListByName
import ferrazfcf.repo_github_api.users_list_and_search.domain.user_list.GetUserListByNameImpl
import ferrazfcf.repo_github_api.users_list_and_search.domain.user_list.GetUserListImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UsersListAndSearchModule {

    @Binds
    @ViewModelScoped
    fun bindGetUserList(getUserListImpl: GetUserListImpl): GetUserList

    @Binds
    @ViewModelScoped
    fun bindGetUserListByName(getUserListByNameImpl: GetUserListByNameImpl): GetUserListByName
}
