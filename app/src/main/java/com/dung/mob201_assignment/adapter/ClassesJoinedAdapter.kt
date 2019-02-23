package com.dung.mob201_assignment.adapter

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import com.dung.mob201_assignment.R
import com.dung.mob201_assignment.model.JoinedClass
import com.dung.mob201_assignment.student.courses.fragment.CoursesJoinedFragment
import kotlinx.android.synthetic.main.list_item_student_joined_classes.view.*

class ClassesJoinedAdapter(var context: Context, var fragment: CoursesJoinedFragment, var list: ArrayList<JoinedClass>)
    : RecyclerView.Adapter<ClassesJoinedAdapter.ClassesHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ClassesHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.list_item_student_joined_classes,p0,false)
        return ClassesHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ClassesHolder, p1: Int) {
        var joinedClass = list.get(p1)
        holder.txtStudentCoursesSeason.text = joinedClass.season
        holder.txtStudentClassName.text = joinedClass.className
        holder.imgRemoveJoinedClass.setOnClickListener {
            fragment.removeJoinedClass(joinedClass.id!!,p1)
        }
        setAnimation(holder.itemView,p1)
    }

    class ClassesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtStudentCoursesSeason = itemView.txtStudentCoursesSeason
        var txtStudentClassName = itemView.txtStudentClassName
        var imgRemoveJoinedClass = itemView.imgRemoveJoinedClass
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