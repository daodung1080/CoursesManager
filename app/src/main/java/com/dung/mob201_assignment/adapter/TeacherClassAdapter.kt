package com.dung.mob201_assignment.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dung.mob201_assignment.model.TeacherClass
import com.dung.mob201_assignment.R
import com.dung.mob201_assignment.teacher.teacherfragment.CoursesListFragment
import kotlinx.android.synthetic.main.list_item_teacher_classes.view.*

class TeacherClassAdapter(var context: Context, var fragment: CoursesListFragment,
                          var list: ArrayList<TeacherClass>): RecyclerView.Adapter<TeacherClassAdapter.ClassHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ClassHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.list_item_teacher_classes,p0,false)
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
        holder.txtClassAmount.text = cl.amount.toString()+" "+context.resources.getString(R.string.Slot)
        holder.imgRemoveClass.setOnClickListener {
            fragment.removeClasses(cl.id!!,p1)
        }
    }

    class ClassHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtClassName = itemView.txtClassName
        var txtClassSchedule = itemView.txtClassSchedule
        var txtClassExamDate = itemView.txtClassExamDate
        var txtClassAmount = itemView.txtClassAmount
        var imgRemoveClass = itemView.imgRemoveClass

    }

}