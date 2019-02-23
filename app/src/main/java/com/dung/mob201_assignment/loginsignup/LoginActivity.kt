package com.dung.mob201_assignment.loginsignup

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.dung.mob201_assignment.BaseActivity
import com.dung.mob201_assignment.R
import com.dung.mob201_assignment.dao.StudentDAO
import com.dung.mob201_assignment.student.StudentActivity
import com.dung.mob201_assignment.teacher.TeacherActivity
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*
import android.R.attr.country
import android.util.DisplayMetrics
import android.content.ContextWrapper
import android.os.Build
import com.facebook.FacebookSdk.getApplicationContext
import android.app.Activity



class LoginActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLoginConfirm.setOnClickListener(this)
        txtSignUp.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if(v == txtSignUp){
            startActivity(Intent(this@LoginActivity,SignUpActivity::class.java))
        }
        else if(v == btnLoginConfirm){
            this.loginValidation()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.language_menu,menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        var id = item!!.itemId
        if(id == R.id.lagEn){
            this.changeLanguage("en")
            this.recreate()
            this.setLanguage("en")
        }else if(id == R.id.lagVn){
            this.changeLanguage("vi")
            this.recreate()
            this.setLanguage("vi")
        }
        return super.onOptionsItemSelected(item)
    }

    fun loginValidation(){
        var studentDAO = StudentDAO(this)
        var studentChecked = rdStudent.isChecked
        var teacherChecked = rdTeacher.isChecked
        var username = edtUsername.text.toString()
        var password = edtPassword.text.toString()

        var sharePreferences = getSharedPreferences("STUDENT", Context.MODE_PRIVATE)
        var editor = sharePreferences.edit()

        if(studentChecked){
            if(username.isEmpty()){
                tilUsername.error = resources.getString(R.string.error_login_username)
            }
            else if(password.isEmpty()){
                tilPassword.error = resources.getString(R.string.error_login_password)
            }
            else if(studentDAO.checkStudentLogin(username,password) == true){
                editor.putString("studentID",username)
                editor.apply()
                this.clearAllEditText()
                this.clearAllTextInputLayout()
                showMessage(resources.getString(R.string.error_login_done))
                startActivity(Intent(this@LoginActivity,StudentActivity::class.java))
                this.finish()
            }
            else if(studentDAO.checkStudentLogin(username,password) == false){
                this.clearAllTextInputLayout()
                showMessage(resources.getString(R.string.error_login_cancel))
            }
        }
        else if(teacherChecked){
            if(username.isEmpty()){
                tilUsername.error = resources.getString(R.string.error_login_username)
            }
            else if(password.isEmpty()){
                tilPassword.error = resources.getString(R.string.error_login_password)
            }
            else if(username.equals("admin") && password.equals("admin")){
                this.clearAllEditText()
                this.clearAllTextInputLayout()
                showMessage(resources.getString(R.string.error_login_done))
                startActivity(Intent(this@LoginActivity,TeacherActivity::class.java))
                this.finish()
            }
            else if(!username.equals("admin") || !password.equals("admin")){
                this.clearAllTextInputLayout()
                showMessage(resources.getString(R.string.error_login_cancel_teacher))
            }
        }
    }

    fun clearAllEditText(){
        edtUsername.setText("")
        edtPassword.setText("")
    }

    fun clearAllTextInputLayout(){
        tilUsername.error = null
        tilPassword.error = null
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

    fun setLanguage(language: String){
        var editor = getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE).edit()
        editor.putString("Lang",language)
        editor.apply()
    }

}
