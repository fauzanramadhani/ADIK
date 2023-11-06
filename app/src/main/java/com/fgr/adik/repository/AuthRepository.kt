package com.fgr.adik.repository

import android.app.Application
import android.content.Intent
import android.util.Log
import com.fgr.adik.BuildConfig
import com.fgr.adik.R
import com.fgr.adik.data.remote.AuthApi
import com.fgr.adik.shared_pref.SharedPreferencesManager
import com.fgr.adik.shared_pref.USER_MONGO_ID_KEY
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val appContext: Application,
    private val authApi: AuthApi,
) {
    private val auth = Firebase.auth
    private val googleSignInOptions =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.oauth_client_id)
            .requestEmail()
            .build()
    private val googleSignInClient: GoogleSignInClient =
        GoogleSignIn.getClient(appContext, googleSignInOptions)
    private val sharedPreferencesManager = SharedPreferencesManager(appContext)

    fun registerWithEmail(
        email: String,
        password: String,
        onComplete: (success: Boolean, data: FirebaseUser?, message: String) -> Unit,
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(
                        true,
                        task.result.user,
                        appContext.getString(R.string.screen_register_successfully)
                    )
                } else {
                    onComplete(false, null, task.exception?.message.toString())
                }
            }
    }

    fun loginWithEmail(
        email: String,
        password: String,
        onComplete: (success: Boolean, message: String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onComplete(true, appContext.getString(R.string.screen_login_successfully))
            }.addOnFailureListener {
                onComplete(false, appContext.getString(R.string.screen_login_failure))
            }
    }

    fun loginWithGoogle() = googleSignInClient.signInIntent

    fun handleGoogleSignInResult(
        data: Intent,
        onComplete: (success: Boolean, firebaseUid: FirebaseUser?, message: String?) -> Unit
    ) {
        val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account: GoogleSignInAccount = task.getResult(ApiException::class.java)!!
            val idToken = account.idToken
            val credential = GoogleAuthProvider.getCredential(idToken, null)

            auth.signInWithCredential(credential)
                .addOnCompleteListener { authResult ->
                    if (authResult.isSuccessful) {

                        onComplete(
                            true,
                            authResult.result.user,
                            null
                        )
                    } else {
                        onComplete(
                            false,
                            null,
                            appContext.getString(R.string.screen_on_boarding_google_login_canceled)
                        )
                    }
                }
        } catch (e: Exception) {
            onComplete(
                false,
                null,
                appContext.getString(R.string.screen_on_boarding_google_login_canceled)
            )
        }
    }

    suspend fun recordLogin(
        firebaseUid: String,
        email: String,
        loginMethod: String,
        onComplete: (success: Boolean, message: String) -> Unit,
    ) {
        try {
            val result = authApi.recordLogin(
                firebaseUid = firebaseUid,
                email = email,
                loginMethods = loginMethod
            )
            if (result.isSuccessful) {
                sharedPreferencesManager.saveString(
                    USER_MONGO_ID_KEY,
                    result.body()?.data?.userMongoId ?: ""
                )
                onComplete(
                    true,
                    appContext.getString(R.string.screen_on_boarding_google_login_successfully)
                )
            } else {
                onComplete(false, result.body()?.message.toString())
                Log.e("AuthRepo", "Result Failure: ${result.message()}")
            }
        } catch (e: Exception) {
            onComplete(false, e.message.toString())
            Log.e("AuthRepo", "Result Error: ${e.message}")
        }
    }

    fun currentUser(): FirebaseUser? {
        auth.currentUser?.reload()
        return auth.currentUser
    }

    fun sendEmailVerification(
        onComplete: (success: Boolean, message: String) -> Unit
    ) {
        auth.currentUser?.sendEmailVerification()?.addOnSuccessListener {
            onComplete(
                true,
                appContext.getString(
                    R.string.screen_email_email_verification_sent_successfully,
                    auth.currentUser?.email ?: ""
                )
            )
        }?.addOnFailureListener {
            onComplete(false, it.message.toString())
        }
    }

    fun logout() {
        auth.signOut()
        googleSignInClient.signOut()
        sharedPreferencesManager.delete(USER_MONGO_ID_KEY)
    }
}