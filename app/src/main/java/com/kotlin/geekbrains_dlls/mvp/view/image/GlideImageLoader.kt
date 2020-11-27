package com.kotlin.geekbrains_dlls.mvp.view.image

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener


class GlideImageLoader : IImageLoader<ImageView> {
    override fun loadImage(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .into(container)
    }
}

