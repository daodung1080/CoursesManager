package com.dung.mob201_assignment

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import android.widget.Toast

open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    fun replaceFragmentToLayout(idLayout: Int, fragment: Fragment){
        if(fragment != null){
            supportFragmentManager.beginTransaction().replace(idLayout,fragment).commit()
        }
    }

    fun showMessage(message: String){
        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
    }

}