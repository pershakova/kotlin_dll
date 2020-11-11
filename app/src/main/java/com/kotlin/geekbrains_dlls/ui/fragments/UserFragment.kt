package com.kotlin.geekbrains_dlls.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kotlin.geekbrains_dlls.R
import com.kotlin.geekbrains_dlls.common.Constants
import com.kotlin.geekbrains_dlls.mvp.model.GithubUser
import moxy.MvpAppCompatFragment

class UserFragment : MvpAppCompatFragment() {
    companion object {
        fun getInstance(repository: GithubUser?): UserFragment? {
            val fragment: UserFragment = UserFragment()
            val args = Bundle()
            args.putParcelable(
                Constants.GithubUser,
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
        val login = view.findViewById<TextView>(R.id.login)
        val bundle = this.arguments

        login.text =
            bundle?.getParcelable<GithubUser>(Constants.GithubUser)?.login
        return view
    }
}