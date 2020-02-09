package com.olux.photag.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.olux.photag.R
import com.olux.photag.databinding.ActivityLoginBinding
import com.olux.photag.models.request.AuthRequest
import com.olux.photag.models.response.AuthResponse
import com.olux.photag.repositories.ApiClient
import com.olux.photag.repositories.MyCallback
import com.olux.photag.ui.home.MainActivity
import com.olux.photag.utils.PrefHelper

private const val WEB_CLIENT_ID =
    "817657172562-aeb4j6pjaia2nl0p42eorcgpe4mf5rj3.apps.googleusercontent.com"

class LoginActivity : AppCompatActivity() {
    companion object {
        const val REQUEST_CODE_SIGN_IN = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil
            .setContentView<ActivityLoginBinding>(
                this,
                R.layout.activity_login
            )
            .apply {
                lifecycleOwner = this@LoginActivity
                signInButton.setOnClickListener { doLogin() }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)
            if (account != null) {
                Log.d("account", account.idToken)
                ApiClient.authService.auth(AuthRequest(account.idToken!!))
                    .enqueue(MyCallback<AuthResponse> {
                        PrefHelper.login(it)
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    })
            }
        }
    }

    private fun doLogin() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(WEB_CLIENT_ID)
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(this, gso)

        startActivityForResult(
            googleSignInClient.signInIntent,
            REQUEST_CODE_SIGN_IN
        )
    }
}
