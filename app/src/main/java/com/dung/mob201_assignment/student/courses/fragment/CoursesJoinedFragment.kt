package com.dung.mob201_assignment.student.courses.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dung.mob201_assignment.R
import com.dung.mob201_assignment.adapter.ClassesJoinedAdapter
import com.dung.mob201_assignment.dao.StudentClassDAO
import com.dung.mob201_assignment.model.JoinedClass
import kotlinx.android.synthetic.main.fragment_student_courses_joined.view.*

class CoursesJoinedFragment: Fragment() {

    var list: ArrayList<JoinedClass>? = null
    var adapter: ClassesJoinedAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootview = inflater.inflate(R.layout.fragment_student_courses_joined,container,false)
        var rvStudentClass = rootview.rvStudentClass

        var studentClassDAO = StudentClassDAO(context!!)
        list = ArrayList()

        var sharedPreferences = context!!.getSharedPreferences("STUDENT",Context.MODE_PRIVATE)
        var studentID = sharedPreferences.getString("studentID","")

        list = studentClassDAO.getAllClassJoined(studentID)
        adapter = ClassesJoinedAdapter(context!!, this,list!!)

        rvStudentClass.layoutManager = LinearLayoutManager(context)
        rvStudentClass.adapter = adapter

        return rootview
    }

    fun removeJoinedClass(id: Int, position: Int){
        var studentClassDAO = StudentClassDAO(context!!)
        var alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle(resources.getString(R.string.log_out_class_title))
        alertDialog.setIcon(R.drawable.img_logout1)
        alertDialog.setMessage(resources.getString(R.string.log_out_class))
        alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { _ , _ ->
            studentClassDAO.removeJoinedClasses(id)
            list!!.removeAt(position)
            adapter!!.notifyDataSetChanged()
            Toast.makeText(context,resources.getString(R.string.log_out_class_yes),Toast.LENGTH_SHORT).show()
        })
        alertDialog.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
        var dialog = alertDialog.create()
        dialog.show()
    }

}