package com.dung.mob201_assignment.Teacher

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.dung.mob201_assignment.BaseActivity
import com.dung.mob201_assignment.R
import com.dung.mob201_assignment.Teacher.TeacherFragment.CoursesListFragment
import com.dung.mob201_assignment.Teacher.TeacherFragment.InsertCoursesClassFragment
import com.dung.mob201_assignment.Teacher.TeacherFragment.InsertCoursesFragment
import com.dung.mob201_assignment.Teacher.TeacherFragment.StudentListFragment
import kotlinx.android.synthetic.main.activity_teacher.*

class TeacherActivity : BaseActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.navigation_home -> {
                fragment = InsertCoursesFragment()
            }
            R.id.class_insert ->{
                fragment = InsertCoursesClassFragment()
            }
            R.id.courses_list ->{
                fragment = CoursesListFragment()
            }
            R.id.navigation_dashboard -> {
                fragment = StudentListFragment()
            }
        }
        this.replaceFragmentToLayout(R.id.flTeacher,fragment!!)
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher)

        var fragment = InsertCoursesFragment()
        this.replaceFragmentToLayout(R.id.flTeacher,fragment)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
