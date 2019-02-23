package com.dung.mob201_assignment.student.facebook

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.dung.mob201_assignment.R
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.util.Base64
import android.util.Log
import com.dung.mob201_assignment.BaseActivity
import com.facebook.*
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_student_facebook.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import org.json.JSONException
import com.facebook.GraphRequest
import com.facebook.AccessToken
import com.facebook.GraphResponse
import com.facebook.share.Share
import com.facebook.share.Sharer
import org.json.JSONObject
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.model.SharePhotoContent
import com.facebook.share.model.SharePhoto
import com.facebook.share.widget.ShareDialog


class StudentFacebookActivity : BaseActivity() {

    var callbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_facebook)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        this.getProfileFacebookData()

        callbackManager = CallbackManager.Factory.create()

        this.activeFacebookButton()
        this.activeFacebookShareButton()

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

    fun setProfileFaceBookData(id: String, name : String){
        var editor = getSharedPreferences("FacebookProfile", Context.MODE_PRIVATE).edit()
        editor.putString("id",id)
        editor.putString("name",name)
        editor.apply()
    }

    fun getProfileFacebookData(){
        var sharedPreferences = getSharedPreferences("FacebookProfile", Context.MODE_PRIVATE)
        var id = sharedPreferences.getString("id","")
        var name = sharedPreferences.getString("name","Facebook Name")
        imgFacebookProfile.profileId = id
        txtFacebookName.setText(name)
    }

    fun getInformationFacebookProfile(accessToken: AccessToken){
        val request = GraphRequest.newMeRequest(
            accessToken
        ) { objectFacebook, response ->
            var id = objectFacebook.getString("id")
            var firstName = objectFacebook.getString("first_name")
            var lastName = objectFacebook.getString("last_name")
            var name = "$firstName $lastName"
            setProfileFaceBookData(id,name)
            imgFacebookProfile.profileId = id
            txtFacebookName.setText(name)
        }
        val parameters = Bundle()
        parameters.putString("fields", "id,first_name,last_name")
        request.parameters = parameters
        request.executeAsync()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager!!.onActivityResult(requestCode,resultCode,data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun activeFacebookButton(){
        btnLoginFaceBook.registerCallback(callbackManager, object : FacebookCallback<LoginResult>{
            override fun onSuccess(result: LoginResult?) {
                val accessToken = AccessToken.getCurrentAccessToken()
                val isLoggedIn = accessToken != null && !accessToken.isExpired
                if(isLoggedIn) {
                    getInformationFacebookProfile(accessToken)
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

    fun activeFacebookShareButton(){
//        var image = BitmapFactory.decodeResource(resources, R.drawable.img_logo)
//        val photo = SharePhoto.Builder()
//            .setBitmap(image)
//            .build()
//        val content = SharePhotoContent.Builder()
//            .addPhoto(photo)
//            .build()
//        btnFacebookShare.shareContent = content

        var shareDialog = ShareDialog(this)
        val content = ShareLinkContent.Builder()
            .setContentUrl(Uri.parse("https://developers.facebook.com"))
            .build()

        btnFacebookShare.shareContent = content

        shareDialog.registerCallback(callbackManager, object: FacebookCallback<Sharer.Result>{
            override fun onSuccess(result: Sharer.Result?) {
                showMessage("Share Complete!")
            }

            override fun onCancel() {
                showMessage("You have Cancel Share")
            }

            override fun onError(error: FacebookException?) {
                showMessage("Error "+error.toString())
            }

        })

    }

}
