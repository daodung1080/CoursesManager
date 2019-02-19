package com.dung.mob201_assignment.LoginSignup

import android.content.Intent
import android.os.Bundle
import com.dung.mob201_assignment.BaseActivity
import com.dung.mob201_assignment.R
import com.dung.mob201_assignment.Student.StudentActivity
import com.dung.mob201_assignment.Teacher.TeacherActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLoginConfirm.setOnClickListener {
            var studentChecked = rdStudent.isChecked
            var teacherChecked = rdTeacher.isChecked

            if(studentChecked){
                startActivity(Intent(this@LoginActivity,StudentActivity::class.java))
            }
            else if(teacherChecked){
                startActivity(Intent(this@LoginActivity,TeacherActivity::class.java))
            }
        }

        txtSignUp.setOnClickListener { startActivity(Intent(this@LoginActivity,SignUpActivity::class.java)) }

    }
}
