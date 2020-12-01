package com.kotlin.geekbrains_dlls.mvp.view.image

interface IImageLoader<T> {
    fun loadImage(url: String, container: T)
}