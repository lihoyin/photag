package com.olux.photag.utils

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.olux.photag.MyApp
import com.olux.photag.models.response.AuthResponse

object PrefHelper {

    val encryptedPref: SharedPreferences by lazy {
        EncryptedSharedPreferences.create(
            "EncryptedSharePref",
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            MyApp.instance,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun login(loginResult: AuthResponse) {
        encryptedPref
            .edit()
            .putString("TOKEN", loginResult.token)
            .putBoolean("IS_LOGIN", true)
            .apply()
    }

    fun logout() {
        encryptedPref.edit().clear().commit()
    }
}

