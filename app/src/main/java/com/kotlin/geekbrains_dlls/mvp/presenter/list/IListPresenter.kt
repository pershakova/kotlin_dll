package com.kotlin.geekbrains_dlls.mvp.presenter.list

import com.kotlin.geekbrains_dlls.mvp.view.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}