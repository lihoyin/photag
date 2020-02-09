package com.olux.photag.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.olux.photag.ui.home.MainActivity
import com.olux.photag.ui.login.LoginActivity
import com.olux.photag.utils.PrefHelper

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isLogin = PrefHelper.encryptedPref.getBoolean("IS_LOGIN", false)
        val intent = Intent(
            this,
            if (isLogin) MainActivity::class.java else LoginActivity::class.java
        )

        startActivity(intent)
        finish()
    }
}
