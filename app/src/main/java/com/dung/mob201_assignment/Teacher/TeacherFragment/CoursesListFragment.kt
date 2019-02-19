package com.dung.mob201_assignment.Teacher.TeacherFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dung.mob201_assignment.R

class CoursesListFragment: Fragment() {

    var rootview: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootview = inflater.inflate(R.layout.fragment_teacher_courses_list,container,false)

        return rootview
    }

}