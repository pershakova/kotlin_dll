package com.kotlin.geekbrains_dlls.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.geekbrains_dlls.R
import com.kotlin.geekbrains_dlls.mvp.presenter.list.IUserListPresenter
import com.kotlin.geekbrains_dlls.mvp.presenter.reposList.IRepoListPresenter
import com.kotlin.geekbrains_dlls.mvp.view.RepoItemView
import com.kotlin.geekbrains_dlls.mvp.view.UserItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_repository.view.*
import kotlinx.android.synthetic.main.item_user.view.*

class RepositoryAdapter(val presenter: IRepoListPresenter) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false))

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer, RepoItemView {
        override fun setName(text: String) = with(containerView) {
            repository_name.text = text
        }

        override var pos = -1
    }
}