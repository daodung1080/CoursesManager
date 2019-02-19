package com.dung.mob201_assignment.Student

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dung.mob201_assignment.R
import com.dung.mob201_assignment.Student.Courses.StudentCoursesActivity
import com.dung.mob201_assignment.Student.Facebook.StudentFacebookActivity
import com.dung.mob201_assignment.Student.Maps.StudentMapsActivity
import com.dung.mob201_assignment.Student.News.StudentNewsActivity
import kotlinx.android.synthetic.main.activity_student.*

class StudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)

        llCourses.setOnClickListener { startActivity(Intent(this@StudentActivity,StudentCoursesActivity::class.java)) }
        llMaps.setOnClickListener { startActivity(Intent(this@StudentActivity, StudentMapsActivity::class.java)) }
        llNews.setOnClickListener { startActivity(Intent(this@StudentActivity, StudentNewsActivity::class.java)) }
        llShare.setOnClickListener { startActivity(Intent(this@StudentActivity, StudentFacebookActivity::class.java)) }

    }
}
