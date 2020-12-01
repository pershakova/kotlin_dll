package com.kotlin.geekbrains_dlls

import android.app.Application
import com.kotlin.geekbrains_dlls.mvp.model.entity.room.Database
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class App : Application() {

    companion object {
         lateinit var instance: App
        lateinit var apiHolder: ApiHolder
    }

    //Временно до даггера положим это тут
    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        apiHolder = ApiHolder
        Database.create(this)
    }

    val navigatorHolder
        get() = cicerone.navigatorHolder

    val router
        get() = cicerone.router

}