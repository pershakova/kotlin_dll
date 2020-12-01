package com.kotlin.geekbrains_dlls.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.geekbrains_dlls.ApiHolder
import com.kotlin.geekbrains_dlls.App
import com.kotlin.geekbrains_dlls.R
import com.kotlin.geekbrains_dlls.common.Constants
import com.kotlin.geekbrains_dlls.mvp.model.GithubUser
import com.kotlin.geekbrains_dlls.mvp.model.RetrofitGithubUsersRepo
import com.kotlin.geekbrains_dlls.mvp.presenter.RepoPresenter
import com.kotlin.geekbrains_dlls.mvp.presenter.UsersPresenter
import com.kotlin.geekbrains_dlls.mvp.view.UserView
import com.kotlin.geekbrains_dlls.mvp.view.UsersView
import com.kotlin.geekbrains_dlls.mvp.view.image.GlideImageLoader
import com.kotlin.geekbrains_dlls.ui.adapter.RepositoryAdapter
import com.kotlin.geekbrains_dlls.ui.adapter.UsersRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_repos.*
import kotlinx.android.synthetic.main.fragment_users.*
import kotlinx.android.synthetic.main.fragment_users.rv_users
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepositoryFragment: MvpAppCompatFragment(), UserView {
    companion object {
        fun getInstance(repository: GithubUser?): RepositoryFragment? {
            val fragment = RepositoryFragment()
            val args = Bundle()
            args.putParcelable(
                Constants.GithubUser,
                repository
            )
            fragment.setArguments(args)
            return fragment
        }
    }
    val presenter: RepoPresenter by moxyPresenter {
        RepoPresenter(AndroidSchedulers.mainThread(), App.instance.router, RetrofitGithubUsersRepo(ApiHolder.api), null)
    }

    var adapter: RepositoryAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = View.inflate(context, R.layout.fragment_repos, null)
        val bundle = this.arguments
        val url = bundle?.getParcelable<GithubUser>(Constants.GithubUser)?.repos_url
        view.findViewById<RecyclerView>(R.id.rv_repos)
        presenter.url = url;
        return view
    }


    override fun init() {
        rv_repos.layoutManager = LinearLayoutManager(context)
        adapter = RepositoryAdapter(presenter.repoListPresenter)
        rv_repos.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }
}