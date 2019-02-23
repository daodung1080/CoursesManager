package com.dung.mob201_assignment.student

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.dung.mob201_assignment.BaseActivity
import com.dung.mob201_assignment.R
import com.dung.mob201_assignment.loginsignup.LoginActivity
import com.dung.mob201_assignment.student.courses.StudentCoursesActivity
import com.dung.mob201_assignment.student.facebook.StudentFacebookActivity
import com.dung.mob201_assignment.student.maps.StudentMapsActivity
import com.dung.mob201_assignment.student.news.StudentNewsActivity
import kotlinx.android.synthetic.main.activity_student.*

class StudentActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)

        llCourses.setOnClickListener { startActivity(Intent(this@StudentActivity,StudentCoursesActivity::class.java)) }
        llMaps.setOnClickListener { startActivity(Intent(this@StudentActivity, StudentMapsActivity::class.java)) }
        llNews.setOnClickListener { startActivity(Intent(this@StudentActivity, StudentNewsActivity::class.java)) }
        llShare.setOnClickListener { startActivity(Intent(this@StudentActivity, StudentFacebookActivity::class.java)) }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.student_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var id = item!!.itemId
        if(id == R.id.studentLogout){
            var alertDialog = AlertDialog.Builder(this)
            alertDialog.setMessage(resources.getString(R.string.log_out_question))
            alertDialog.setTitle(resources.getString(R.string.title_log_out))
            alertDialog.setIcon(R.drawable.img_logout1)
            alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
                startActivity(Intent(this@StudentActivity, LoginActivity::class.java))
                this.finish()
            })
            alertDialog.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
            var dialog = alertDialog.create()
            dialog.show()
        }
        return super.onOptionsItemSelected(item)
    }

}
