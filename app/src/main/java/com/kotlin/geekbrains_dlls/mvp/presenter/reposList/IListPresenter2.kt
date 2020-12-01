package com.kotlin.geekbrains_dlls.mvp.presenter.reposList

import com.kotlin.geekbrains_dlls.mvp.view.IItemView

interface IListPresenter2<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}