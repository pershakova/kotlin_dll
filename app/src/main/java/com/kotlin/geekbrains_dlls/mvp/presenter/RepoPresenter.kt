package com.kotlin.geekbrains_dlls.mvp.presenter

import com.kotlin.geekbrains_dlls.mvp.model.GithubRepository
import com.kotlin.geekbrains_dlls.mvp.model.GithubUser
import com.kotlin.geekbrains_dlls.mvp.model.IGithubUsersRepo
import com.kotlin.geekbrains_dlls.mvp.presenter.list.IUserListPresenter
import com.kotlin.geekbrains_dlls.mvp.presenter.reposList.IRepoListPresenter
import com.kotlin.geekbrains_dlls.mvp.view.RepoItemView
import com.kotlin.geekbrains_dlls.mvp.view.UserItemView
import com.kotlin.geekbrains_dlls.mvp.view.UserView
import com.kotlin.geekbrains_dlls.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class RepoPresenter(val mainThreadScheduler: Scheduler, val router: Router, val repo: IGithubUsersRepo, var url: String?) : MvpPresenter<UserView>() {

    class RepoListPresenter : IRepoListPresenter {
        val repos = mutableListOf<GithubRepository>()
        override var itemClickListener: ((RepoItemView) -> Unit)? = null
        override fun getCount() = repos.size

        override fun bindView(view: RepoItemView) {
            val repo = repos[view.pos]
            repo.name?.let { view.setName(it) }
        }
    }
    
    val repoListPresenter = RepoListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        repoListPresenter.itemClickListener = { itemView ->
            val repositoryInfo = repoListPresenter.repos[itemView.pos]
            router.navigateTo(Screens.RepositoryInfoScreen(repositoryInfo))
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun loadData() {
        url?.let {
            repo.getRepos(it)
                .observeOn(mainThreadScheduler)
                .subscribe({ repository ->
                    repoListPresenter.repos.clear()
                    repoListPresenter.repos.addAll(repository)
                    viewState.updateList()
                }, {
                    println("Error: ${it.message}")
                })
        }
    }

}