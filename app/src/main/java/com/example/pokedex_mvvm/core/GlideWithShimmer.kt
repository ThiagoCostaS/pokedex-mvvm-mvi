package com.example.pokedex_mvvm.core

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pokedex_mvvm.R
import com.facebook.shimmer.ShimmerFrameLayout

object GlideWithShimmer{
    fun loadImageWithShimmer(
        imageView: ImageView,
        imageUrl: String,
        shimmer: ShimmerFrameLayout
    ) {
        val requestBuilder = Glide.with(imageView.context)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder_img)
            .error(R.drawable.error_img)
            .centerCrop()
            .addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    shimmer.hideShimmer()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    shimmer.hideShimmer()
                    return false
                }
            })

        shimmer.startShimmer()

        requestBuilder.into(imageView)
    }
}