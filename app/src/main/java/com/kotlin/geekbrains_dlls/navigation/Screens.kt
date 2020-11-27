package com.kotlin.geekbrains_dlls.navigation

import com.kotlin.geekbrains_dlls.mvp.model.GithubRepository
import com.kotlin.geekbrains_dlls.mvp.model.GithubUser
import com.kotlin.geekbrains_dlls.ui.fragments.RepositoryFragment
import com.kotlin.geekbrains_dlls.ui.fragments.RepositoryInfoFragment
import com.kotlin.geekbrains_dlls.ui.fragments.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class UsersScreen() : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }

    class UserScreen(repository: GithubUser) : SupportAppScreen() {
        val repository = repository
        override fun getFragment() = RepositoryFragment.getInstance(repository)
    }

    class RepositoryInfoScreen(repository: GithubRepository) : SupportAppScreen() {
        val repository = repository
        override fun getFragment() = RepositoryInfoFragment.getInstance(repository)
    }
}