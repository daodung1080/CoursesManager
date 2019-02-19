package com.dung.mob201_assignment.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dung.mob201_assignment.Model.Student
import com.dung.mob201_assignment.R
import com.dung.mob201_assignment.Teacher.TeacherFragment.StudentListFragment
import kotlinx.android.synthetic.main.list_item_teacher_student.view.*

class StudentAdapter(var context: Context, var fragment: StudentListFragment, var list: ArrayList<Student>)
    : RecyclerView.Adapter<StudentAdapter.StudentHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): StudentHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.list_item_teacher_student,p0,false)
        return StudentHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: StudentHolder, p1: Int) {
        var student = list.get(p1)
        holder.txtStudentFullName.text = student.fullname
        holder.txtStudentID.text = student.id
        holder.imgRemoveStudent.setOnClickListener { Toast.makeText(context,"I am Student Adapter",Toast.LENGTH_SHORT).show() }
    }

    class StudentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtStudentFullName = itemView.txtStudentFullName
        var txtStudentID = itemView.txtStudentID
        var imgRemoveStudent = itemView.imgRemoveStudent
    }
}