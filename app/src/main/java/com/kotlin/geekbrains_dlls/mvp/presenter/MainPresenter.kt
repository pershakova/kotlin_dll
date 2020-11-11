package com.kotlin.geekbrains_dlls.mvp.presenter

import com.kotlin.geekbrains_dlls.mvp.view.MainView
import com.kotlin.geekbrains_dlls.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class MainPresenter(val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.UsersScreen())
    }

    fun backClicked() {
        router.exit()
    }
}