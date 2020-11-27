package com.kotlin.geekbrains_dlls.mvp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.Expose
import retrofit2.http.Url

@Parcelize
data class GithubUser(
    @Expose val id: String? = null,
    @Expose val login: String? = null,
    @Expose val avatarUrl: String? = null,
    @Expose val repos_url: String? = null
) : Parcelable
