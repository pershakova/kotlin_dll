package com.kotlin.geekbrains_dlls.mvp.presenter

import com.kotlin.geekbrains_dlls.mvp.model.CountersModel
import com.kotlin.geekbrains_dlls.mvp.view.MainView

class MainPresenter(val view: MainView) {
    val model = CountersModel()

    fun counterClick1(index: Int){
        view.setButtonText1(model.next(index).toString())
    }

    fun counterClick2(index: Int){
        view.setButtonText2(model.next(index).toString())
    }

    fun counterClick3(index: Int){
        view.setButtonText3(model.next(index).toString())
    }
}