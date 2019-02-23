package com.dung.mob201_assignment.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dung.mob201_assignment.R
import com.dung.mob201_assignment.model.TeacherClass
import com.dung.mob201_assignment.student.courses.fragment.CoursesParticipateFragment
import kotlinx.android.synthetic.main.list_item_student_classes.view.*
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import java.util.*


class ClassesParticipateAdapter(var context: Context, var fragment: CoursesParticipateFragment, var list: ArrayList<TeacherClass>)
    : RecyclerView.Adapter<ClassesParticipateAdapter.ClassesHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ClassesHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.list_item_student_classes,p0,false)
        return ClassesHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ClassesHolder, p1: Int) {
        var teacherClass = list.get(p1)
        holder.txtStudentClass.text = teacherClass.name
        holder.imgJoinClass.setOnClickListener {
            fragment.participateClass(p1)
        }
        holder.imgFindClass.setOnClickListener {
            fragment.lookingForRemainSlot(p1)
        }
        setAnimation(holder.itemView,p1)
    }

    class ClassesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtStudentClass = itemView.txtStudentClass
        var imgJoinClass = itemView.imgJoinClass
        var imgFindClass = itemView.imgFindClass
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