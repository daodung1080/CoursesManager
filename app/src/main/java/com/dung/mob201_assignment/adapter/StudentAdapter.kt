package com.dung.mob201_assignment.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.Toast
import com.dung.mob201_assignment.model.Student
import com.dung.mob201_assignment.R
import com.dung.mob201_assignment.teacher.teacherfragment.StudentListFragment
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
        holder.imgRemoveStudent.setOnClickListener {
            fragment.removeStudent(student.id,p1)
        }
        setAnimation(holder.itemView,p1)
    }

    class StudentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtStudentFullName = itemView.txtStudentFullName
        var txtStudentID = itemView.txtStudentID
        var imgRemoveStudent = itemView.imgRemoveStudent
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        var lastPosition = -1
        if (position > lastPosition) {
            val anim = ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            anim.duration = 700
            viewToAnimate.startAnimation(anim)
            lastPosition = position
        }
    }

}