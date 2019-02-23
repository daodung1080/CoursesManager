package com.dung.mob201_assignment.teacher.teacherfragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dung.mob201_assignment.adapter.StudentAdapter
import com.dung.mob201_assignment.dao.StudentDAO
import com.dung.mob201_assignment.model.Student
import com.dung.mob201_assignment.R
import com.dung.mob201_assignment.dao.StudentClassDAO
import kotlinx.android.synthetic.main.fragment_teacher_student_list.view.*

class StudentListFragment: Fragment() {

    var list: ArrayList<Student>? = null
    var adapter: StudentAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootview = inflater.inflate(R.layout.fragment_teacher_student_list,container,false)
        var rvTeacherStudentList = rootview.rvTeacherStudentList

        var studentDAO = StudentDAO(context!!)
        list = ArrayList()
        list = studentDAO.getAllStudent()
        adapter = StudentAdapter(context!!,this,list!!)

        rvTeacherStudentList.layoutManager = LinearLayoutManager(context)
        rvTeacherStudentList.adapter = adapter

        return rootview
    }

    fun removeStudent(sID: String,position: Int){
        var studentClassDAO = StudentDAO(context!!)
        var alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle(resources.getString(R.string.log_out_teacher_student_title))
        alertDialog.setIcon(R.drawable.img_logout1)
        alertDialog.setMessage(resources.getString(R.string.log_out_teacher_student))
        alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
            studentClassDAO.removeStudent(sID)
            list!!.removeAt(position)
            adapter!!.notifyDataSetChanged()
            Toast.makeText(context,resources.getString(R.string.log_out_teacher_student_yes), Toast.LENGTH_SHORT).show()
        })
        alertDialog.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
        var dialog = alertDialog.create()
        dialog.show()
    }

}