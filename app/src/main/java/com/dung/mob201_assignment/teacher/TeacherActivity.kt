package com.dung.mob201_assignment.teacher

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.dung.mob201_assignment.BaseActivity
import com.dung.mob201_assignment.R
import com.dung.mob201_assignment.loginsignup.LoginActivity
import com.dung.mob201_assignment.teacher.teacherfragment.CoursesListFragment
import com.dung.mob201_assignment.teacher.teacherfragment.InsertCoursesClassFragment
import com.dung.mob201_assignment.teacher.teacherfragment.InsertCoursesFragment
import com.dung.mob201_assignment.teacher.teacherfragment.StudentListFragment
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.teacher_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        var id = item!!.itemId
        if(id == R.id.teacherLogout){
            var alertDialog = AlertDialog.Builder(this)
            alertDialog.setMessage(resources.getString(R.string.log_out_question))
            alertDialog.setTitle(resources.getString(R.string.title_log_out))
            alertDialog.setIcon(R.drawable.img_logout1)
            alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { _ , _ ->
                startActivity(Intent(this@TeacherActivity,LoginActivity::class.java))
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
