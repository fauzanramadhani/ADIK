package com.fgr.adik.repository

import android.app.Application
import android.content.Intent
import com.fgr.adik.BuildConfig
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val appContext: Application,
) {
    private val auth = Firebase.auth
    private val googleSignInOptions =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.oauth_client_id)
            .requestEmail()
            .build()
    private val googleSignInClient: GoogleSignInClient =
        GoogleSignIn.getClient(appContext, googleSignInOptions)

    fun registerWithEmail(
        email: String,
        password: String,
        onComplete: (success: Boolean) -> Unit,
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(true)
                } else {
                    onComplete(false)
                }
            }
    }

    fun loginWithEmail(
        email: String,
        password: String,
        onComplete: (success: Boolean) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(true)
                } else {
                    onComplete(false)
                }
            }
    }

    fun loginWithGoogle() = googleSignInClient.signInIntent

    fun handleGoogleSignInResult(data: Intent, onComplete: (success: Boolean) -> Unit) {
        val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account: GoogleSignInAccount = task.getResult(ApiException::class.java)!!
            val idToken = account.idToken
            val credential = GoogleAuthProvider.getCredential(idToken, null)

            auth.signInWithCredential(credential)
                .addOnCompleteListener { authResult ->
                    if (authResult.isSuccessful) {
                        onComplete(true)
                    } else {
                        onComplete(false)
                    }
                }
        } catch (e: Exception) {
            onComplete(false)
        }
    }

    fun currentUser() = auth.currentUser

    fun isEmailVerified(): Boolean {
        auth.currentUser?.reload()
        return auth.currentUser?.isEmailVerified == true
    }

    fun logout() {
        auth.signOut()
        googleSignInClient.signOut()
    }
}