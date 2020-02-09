package com.olux.photag

import android.app.Application
import android.content.Intent
import com.olux.photag.ui.login.LoginActivity
import com.olux.photag.utils.PrefHelper

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: MyApp
            private set
    }

    fun logout() {
        PrefHelper.logout()

        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}