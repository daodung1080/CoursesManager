package com.dung.mob201_assignment

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.dung.mob201_assignment.loginsignup.LoginActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*
import java.util.*
import kotlin.concurrent.schedule

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getLanguage()
        setContentView(R.layout.activity_splash_screen)

        this.splashScreen()

    }

    fun splashScreen(){

        var animationUtils = AnimationUtils.loadAnimation(this,R.anim.loading_anim)
        animationUtils.duration = 7000
        imgLoading.startAnimation(animationUtils)

        var timer = Timer()
        timer.schedule(4000){
            startActivity(Intent(this@SplashScreen,LoginActivity::class.java))
            finish()
        }
    }

    fun changeLanguage(language: String?) {
        val myLocale = Locale(language)
        val res = resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, dm)
    }

    fun getLanguage(){
        var sharedPreferences = getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE)
        var lang = sharedPreferences.getString("Lang","")
        changeLanguage(lang)
    }

}
