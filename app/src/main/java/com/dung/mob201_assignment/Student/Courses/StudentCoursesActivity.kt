package com.dung.mob201_assignment.Student.Courses

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.dung.mob201_assignment.BaseActivity
import com.dung.mob201_assignment.R
import com.dung.mob201_assignment.Student.Courses.Fragment.CoursesJoinedFragment
import com.dung.mob201_assignment.Student.Courses.Fragment.CoursesParticipateFragment
import com.dung.mob201_assignment.Student.Courses.Fragment.ExamListFragment
import kotlinx.android.synthetic.main.activity_student_courses.*

class StudentCoursesActivity : BaseActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.navigation_home -> fragment = CoursesParticipateFragment()
            R.id.navigation_dashboard -> fragment = CoursesJoinedFragment()
            R.id.navigation_notifications -> fragment = ExamListFragment()
        }
        this.replaceFragmentToLayout(R.id.flStudent,fragment!!)
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_courses)

        var fragment: Fragment = CoursesParticipateFragment()
        this.replaceFragmentToLayout(R.id.flStudent,fragment!!)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
