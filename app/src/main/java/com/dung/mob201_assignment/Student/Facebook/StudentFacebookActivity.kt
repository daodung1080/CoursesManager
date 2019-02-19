package com.dung.mob201_assignment.Student.Facebook

import android.content.Intent
import android.os.Bundle
import com.dung.mob201_assignment.R
import android.content.pm.PackageManager
import android.util.Base64
import android.util.Log
import com.dung.mob201_assignment.BaseActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_student_facebook.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import com.facebook.AccessToken




class StudentFacebookActivity : BaseActivity() {

    var callbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_facebook)

        callbackManager = CallbackManager.Factory.create()

    }

    fun activeFacebookButton(){
        btnLoginFaceBook.setReadPermissions("email")
        btnLoginFaceBook.registerCallback(callbackManager, object : FacebookCallback<LoginResult>{
            override fun onSuccess(result: LoginResult?) {
                val accessToken = AccessToken.getCurrentAccessToken()
                val isLoggedIn = accessToken != null && !accessToken.isExpired
                if(isLoggedIn){
                    showMessage("Login Complete!")
                }
            }

            override fun onCancel() {
                    showMessage("Login Cancelled")
            }

            override fun onError(error: FacebookException?) {
                    showMessage("Error "+error.toString())
            }

        })
    }

    fun getFacebookHashKey(){
        try {
            val info = packageManager.getPackageInfo(
                "com.dung.mob201_assignment",
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {

        } catch (e: NoSuchAlgorithmException) {

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager!!.onActivityResult(requestCode,resultCode,data)
        super.onActivityResult(requestCode, resultCode, data)
    }

}
