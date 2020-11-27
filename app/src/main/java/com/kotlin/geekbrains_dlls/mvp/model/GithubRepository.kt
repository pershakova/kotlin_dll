package com.kotlin.geekbrains_dlls.mvp.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubRepository(
    @Expose val name: String? = null,
    @Expose val fork: String? = null,
    @Expose val forks: String? = null
) : Parcelable