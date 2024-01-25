package com.digitalhorizons.kidshistory

import android.app.Application
import com.digitalhorizons.kidshistory.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

internal class MyApplication: Application()  {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(viewModelModule)
        }

    }
}