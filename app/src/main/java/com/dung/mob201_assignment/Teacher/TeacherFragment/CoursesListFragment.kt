package com.dung.mob201_assignment.Teacher.TeacherFragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.LinearLayout
import com.dung.mob201_assignment.Adapter.TeacherClassAdapter
import com.dung.mob201_assignment.Adapter.TeacherCoursesAdapter
import com.dung.mob201_assignment.DAO.TeacherClassDAO
import com.dung.mob201_assignment.DAO.TeacherCousesDAO
import com.dung.mob201_assignment.Model.TeacherClass
import com.dung.mob201_assignment.Model.TeacherCourses
import com.dung.mob201_assignment.R
import kotlinx.android.synthetic.main.dialog_teacher_courses_list.view.*
import kotlinx.android.synthetic.main.fragment_teacher_courses_list.view.*

class CoursesListFragment: Fragment() {

    var list: ArrayList<TeacherCourses>? = null
    var adapter: TeacherCoursesAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootview = inflater.inflate(R.layout.fragment_teacher_courses_list,container,false)
        var lvTeacherCoursesList = rootview.lvTeacherCoursesList

        var teacherCoursesDAO = TeacherCousesDAO(context!!)


        list = ArrayList()
        list = teacherCoursesDAO.getAllTeacherCourses()

        adapter = TeacherCoursesAdapter(context!!,this,list!!)

        lvTeacherCoursesList.adapter = adapter

        lvTeacherCoursesList.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            var teacherCourses = list!!.get(position)
            this.createAlertDialog(teacherCourses.season)
        })

        return rootview
    }

    fun createAlertDialog(coursesSeason: String){
        var teacherClassDAO = TeacherClassDAO(context!!)
        var clList: ArrayList<TeacherClass> = ArrayList()
        clList = teacherClassDAO.getAllTeacherClass(coursesSeason)
        var clAdapter = TeacherClassAdapter(context!!,this,clList)

        var alertDialog = AlertDialog.Builder(context)
        alertDialog.setIcon(R.drawable.img_course)
        alertDialog.setTitle(R.string.title_dialog_class)

        var view = layoutInflater.inflate(R.layout.dialog_teacher_courses_list,null)
        var rvTeacherClass = view.rvTeacherClass

        rvTeacherClass.layoutManager = LinearLayoutManager(context)
        rvTeacherClass.adapter = clAdapter

        alertDialog.setView(view)
        alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

        var dialog = alertDialog.create()
        dialog.show()

    }

}