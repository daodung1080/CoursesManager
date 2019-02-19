package com.dung.mob201_assignment.LoginSignup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dung.mob201_assignment.BaseActivity
import com.dung.mob201_assignment.DAO.StudentDAO
import com.dung.mob201_assignment.Model.Student
import com.dung.mob201_assignment.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btnSignUp.setOnClickListener {
            this.validateSignUp()
        }

    }

    fun validateSignUp(){
        var studentDAO = StudentDAO(this)
        var studentID = edtStudentID.text.toString()
        var studentFullName = edtFullname.text.toString()
        var studentPassword = edtNewPassword.text.toString()
        if(studentID.length != 5){
            tilStudentID.error = resources.getString(R.string.error_student_id)
        }
        else if(studentFullName.length < 5){
            tilFullname.error = resources.getString(R.string.error_student_fullname)
        }
        else if(studentPassword.length < 5){
            tilNewPassword.error = resources.getString(R.string.error_student_password)
        }
        else if(studentDAO.insertStudent(Student(studentID,studentFullName,studentPassword)) > 0){
            showMessage(resources.getString(R.string.error_student_done))
            this.clearAllTextInputLayoutError()
            this.clearAllEditText()
        }
        else if(studentDAO.insertStudent(Student(studentID,studentFullName,studentPassword)) <= 0){
            this.clearAllTextInputLayoutError()
            showMessage(resources.getString(R.string.error_student_cancel))
        }
    }

    fun clearAllEditText(){
        edtFullname.setText("")
        edtNewPassword.setText("")
        edtStudentID.setText("")
    }

    fun clearAllTextInputLayoutError(){
        tilNewPassword.error = null
        tilFullname.error = null
        tilStudentID.error = null
    }

}
