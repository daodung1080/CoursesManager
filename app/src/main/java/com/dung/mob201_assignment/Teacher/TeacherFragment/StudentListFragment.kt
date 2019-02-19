package com.dung.mob201_assignment.Teacher.TeacherFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dung.mob201_assignment.Adapter.StudentAdapter
import com.dung.mob201_assignment.DAO.StudentDAO
import com.dung.mob201_assignment.Model.Student
import com.dung.mob201_assignment.R
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

}