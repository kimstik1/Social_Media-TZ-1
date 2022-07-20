package com.kimstik.social_media

import android.app.Application
import com.kimstik.social_media.di.AppComponent
import com.kimstik.social_media.di.AppModule
import com.kimstik.social_media.di.DaggerAppComponent

class AppApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this

        appComponent = DaggerAppComponent.builder().appModule(AppModule(applicationContext)).build()
    }

    companion object {
        internal lateinit var INSTANCE: AppApplication
            private set
    }
}