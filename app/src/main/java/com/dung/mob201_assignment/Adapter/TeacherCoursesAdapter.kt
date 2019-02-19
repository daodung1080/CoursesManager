package com.dung.mob201_assignment.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.dung.mob201_assignment.Model.TeacherCourses
import com.dung.mob201_assignment.R
import com.dung.mob201_assignment.Teacher.TeacherFragment.CoursesListFragment
import kotlinx.android.synthetic.main.list_item_teacher_courses.view.*

class TeacherCoursesAdapter(var context: Context, var fragment: CoursesListFragment,
                            var list: ArrayList<TeacherCourses>): BaseAdapter() {

    class ViewHolder(view: View){
        var txtCoursesName: TextView
        var txtCoursesRemainSlot: TextView
        var imgRemoveCourses: ImageView
        init {
            txtCoursesName = view.txtCoursesName
            txtCoursesRemainSlot = view.txtCoursesRemainSlot
            imgRemoveCourses = view.imgRemoveCourses
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder: ViewHolder
        var view: View
        if(convertView == null){
            var layoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.list_item_teacher_courses,null)
            holder = ViewHolder(view)
            view.tag = holder
        }else{
            view = convertView
            holder = view.tag as ViewHolder
        }

        var tcourses: TeacherCourses = getItem(position) as TeacherCourses
        holder.txtCoursesName.text = tcourses.season
        holder.txtCoursesRemainSlot.text = tcourses.amount.toString()
        holder.imgRemoveCourses.setOnClickListener { Toast.makeText(context,"I am Teacher Courses Adapter",Toast.LENGTH_SHORT).show() }

        return view
    }

    override fun getItem(position: Int): Any {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }
}