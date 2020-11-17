package com.kotlin.geekbrains_dlls.mvp.presenter

import android.util.Log
import com.kotlin.geekbrains_dlls.mvp.model.GithubUser
import com.kotlin.geekbrains_dlls.mvp.model.GithubUsersRepo
import com.kotlin.geekbrains_dlls.mvp.presenter.list.IUserListPresenter
import com.kotlin.geekbrains_dlls.mvp.view.UserItemView
import com.kotlin.geekbrains_dlls.mvp.view.UsersView
import com.kotlin.geekbrains_dlls.navigation.Screens
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.functions.Consumer
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router


class UsersPresenter(val usersRepo: GithubUsersRepo, val router: Router) : MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(Screens.UserScreen(usersListPresenter.users[itemView.pos]))
        }
    }

    fun loadData() {
        val users = usersRepo.getUsers()
      /*  users.subscribe(
           { s -> usersListPresenter.users.add(s) },
            { e -> println(e) },
            {      println("onComplete") }
        ) */

        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}