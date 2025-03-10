package com.demo.countriesdemo

import android.app.Application
import com.demo.countriesdemo.di.AppLevelModule
import com.demo.countriesdemo.di.AppLevelModuleImpl

class MyApp: Application() {
    companion object{
        lateinit var appLevelModule: AppLevelModule
    }

    override fun onCreate() {
        super.onCreate()
        appLevelModule = AppLevelModuleImpl()
    }
}