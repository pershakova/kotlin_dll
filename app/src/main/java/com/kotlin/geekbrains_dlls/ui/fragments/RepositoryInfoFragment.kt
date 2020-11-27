package com.kotlin.geekbrains_dlls.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kotlin.geekbrains_dlls.R
import com.kotlin.geekbrains_dlls.common.Constants
import com.kotlin.geekbrains_dlls.mvp.model.GithubRepository
import com.kotlin.geekbrains_dlls.mvp.model.GithubUser
import moxy.MvpAppCompatFragment

class RepositoryInfoFragment : MvpAppCompatFragment() {
    companion object {
        fun getInstance(repository: GithubRepository?): RepositoryInfoFragment? {
            val fragment = RepositoryInfoFragment()
            val args = Bundle()
            args.putParcelable(
                Constants.GithubRepositoryInfo,
                repository
            )
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = View.inflate(context, R.layout.fragment_user, null)
        val forkText = view.findViewById<TextView>(R.id.fork)
        val forksText = view.findViewById<TextView>(R.id.forks)
        val bundle = this.arguments

        forkText.text =
            bundle?.getParcelable<GithubRepository>(Constants.GithubRepositoryInfo)?.fork

        forksText.text =
            bundle?.getParcelable<GithubRepository>(Constants.GithubRepositoryInfo)?.forks
        return view
    }
}