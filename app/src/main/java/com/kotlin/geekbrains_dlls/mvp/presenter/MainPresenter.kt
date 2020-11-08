package com.kotlin.geekbrains_dlls.mvp.presenter

import com.kotlin.geekbrains_dlls.mvp.model.CountersModel
import com.kotlin.geekbrains_dlls.mvp.view.MainView

class MainPresenter(val view: MainView) {
    val model = CountersModel()

    fun counterClick1(index: Int) : String{
        return model.next(index).toString()
    }
}