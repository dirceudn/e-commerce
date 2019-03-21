package com.google.android.commerce.injection

import android.content.Context
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.load.model.GlideUrl
import com.google.android.commerce.data.remote.UnsafeOkHttpClient
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import java.io.InputStream


@GlideModule
open class CommerceGlideModule : AppGlideModule(){

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val client = UnsafeOkHttpClient.getUnsafeOkHttpClient()
        registry.replace(
            GlideUrl::class.java, InputStream::class.java,
            OkHttpUrlLoader.Factory(client)
        )
    }
}