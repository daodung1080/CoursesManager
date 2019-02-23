package com.dung.mob201_assignment.teacher.teacherfragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.dung.mob201_assignment.adapter.TeacherClassAdapter
import com.dung.mob201_assignment.adapter.TeacherCoursesAdapter
import com.dung.mob201_assignment.dao.TeacherClassDAO
import com.dung.mob201_assignment.dao.TeacherCousesDAO
import com.dung.mob201_assignment.model.TeacherClass
import com.dung.mob201_assignment.model.TeacherCourses
import com.dung.mob201_assignment.R
import com.dung.mob201_assignment.dao.StudentClassDAO
import kotlinx.android.synthetic.main.dialog_teacher_courses_list.view.*
import kotlinx.android.synthetic.main.fragment_teacher_courses_list.view.*

class CoursesListFragment: Fragment() {

    var list: ArrayList<TeacherCourses>? = null
    var adapter: TeacherCoursesAdapter? = null
    var clAdapter: TeacherClassAdapter? = null
    var clList: ArrayList<TeacherClass>? = null

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
        clList = ArrayList()
        clList = teacherClassDAO.getAllTeacherClass(coursesSeason)
        clAdapter = TeacherClassAdapter(context!!,this,clList!!)

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

    fun removeCourses(cSeason: String, position: Int){
        var teacherClassDAO = TeacherClassDAO(context!!)
        var teacherCoursesDAO = TeacherCousesDAO(context!!)

        var alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle(resources.getString(R.string.log_out_teacher_courses_title))
        alertDialog.setIcon(R.drawable.img_logout1)
        alertDialog.setMessage(resources.getString(R.string.log_out_teacher_courses))
        alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { _ , _ ->
            teacherClassDAO.removeTeacherClass(1,cSeason,2)
            teacherCoursesDAO.removeTeacherCourses(cSeason)
            list!!.removeAt(position)
            adapter!!.notifyDataSetChanged()
            Toast.makeText(context,resources.getString(R.string.log_out_teacher_courses_yes), Toast.LENGTH_SHORT).show()
        })
        alertDialog.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
        var dialog = alertDialog.create()
        dialog.show()
    }

    fun removeClasses(cID: Int, position: Int){
        var teacherClassDAO = TeacherClassDAO(context!!)

        var alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle(resources.getString(R.string.log_out_teacher_class_title))
        alertDialog.setIcon(R.drawable.img_logout1)
        alertDialog.setMessage(resources.getString(R.string.log_out_teacher_class))
        alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { _ , _ ->
            teacherClassDAO.removeTeacherClass(cID,"",1)
            clList!!.removeAt(position)
            clAdapter!!.notifyDataSetChanged()
            Toast.makeText(context,resources.getString(R.string.log_out_teacher_classes_yes), Toast.LENGTH_SHORT).show()
        })
        alertDialog.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
        var dialog = alertDialog.create()
        dialog.show()
    }

}