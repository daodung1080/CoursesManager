package com.dung.mob201_assignment.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dung.mob201_assignment.Model.TeacherClass
import com.dung.mob201_assignment.R
import com.dung.mob201_assignment.Teacher.TeacherFragment.CoursesListFragment
import kotlinx.android.synthetic.main.list_item_teacher_class.view.*

class TeacherClassAdapter(var context: Context, var fragment: CoursesListFragment,
                          var list: ArrayList<TeacherClass>): RecyclerView.Adapter<TeacherClassAdapter.ClassHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ClassHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.list_item_teacher_class,p0,false)
        return ClassHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ClassHolder, p1: Int) {
        var cl = list.get(p1)
        holder.txtClassName.text = cl.name
        holder.txtClassSchedule.text = cl.schedule
        holder.txtClassExamDate.text = cl.examDate
        holder.imgRemoveClass.setOnClickListener { Toast.makeText(context,"I am Class Adapter",Toast.LENGTH_SHORT).show() }
    }

    class ClassHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtClassName = itemView.txtClassName
        var txtClassSchedule = itemView.txtClassSchedule
        var txtClassExamDate = itemView.txtClassExamDate
        var imgRemoveClass = itemView.imgRemoveClass

    }

}