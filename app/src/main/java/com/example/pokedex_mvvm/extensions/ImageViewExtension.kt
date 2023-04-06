package com.example.pokedex_mvvm.extensions

import android.widget.ImageView
import com.example.pokedex_mvvm.core.GlideWithShimmer
import com.facebook.shimmer.ShimmerFrameLayout

fun ImageView.loadImageWithShimmer(
    imageUrl: String,
    shimmer: ShimmerFrameLayout
) {
    GlideWithShimmer.loadImageWithShimmer(
        imageView = this,
        imageUrl = imageUrl,
        shimmer = shimmer
    )
}