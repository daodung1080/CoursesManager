package com.dung.mob201_assignment.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.dung.mob201_assignment.R
import com.dung.mob201_assignment.model.ClassInformation
import kotlinx.android.synthetic.main.list_item_student_exam.view.*

class ExamAdapter(var context: Context, var list: ArrayList<ClassInformation>)
    : RecyclerView.Adapter<ExamAdapter.ExamHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ExamHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.list_item_student_exam,p0,false)
        return ExamHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ExamHolder, p1: Int) {
        var classInformation = list.get(p1)
        setAnimation(holder.itemView,p1)

        holder.txtExamClassName.text = classInformation.className
        holder.txtExamSchedule.text = classInformation.schedule
        holder.txtExamDate.text = classInformation.exam
        holder.txtExamClassSeason.text = classInformation.season

    }

    class ExamHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtExamClassName = itemView.txtExamClassName
        var txtExamSchedule = itemView.txtExamSchedule
        var txtExamDate = itemView.txtExamDate
        var txtExamClassSeason = itemView.txtExamClassSeason
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