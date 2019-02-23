package com.dung.mob201_assignment.student.courses.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.dung.mob201_assignment.R
import com.dung.mob201_assignment.adapter.ClassesParticipateAdapter
import com.dung.mob201_assignment.dao.StudentClassDAO
import com.dung.mob201_assignment.dao.TeacherClassDAO
import com.dung.mob201_assignment.dao.TeacherCousesDAO
import com.dung.mob201_assignment.model.JoinedClass
import com.dung.mob201_assignment.model.TeacherClass
import kotlinx.android.synthetic.main.fragment_student_courses_participate.view.*

class CoursesParticipateFragment: Fragment() {

    var list: ArrayList<TeacherClass>? = null
    var adapter: ClassesParticipateAdapter? = null
    var spnList: ArrayList<String>? = null
    var spnPosition: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootview = inflater.inflate(R.layout.fragment_student_courses_participate,container,false)
        var spnStudentCourses = rootview.spnStudentCourses
        var btnStudentCoursesSearch = rootview.btnStudentCoursesSearch
        var rvClassesParticipate = rootview.rvClassesParticipate

        var teacherClassDAO = TeacherClassDAO(context!!)
        var teacherCoursesDAO = TeacherCousesDAO(context!!)

        spnList = ArrayList()
        spnList = teacherCoursesDAO.getAllCoursesSeason()

        var spnAdapter: ArrayAdapter<String> = ArrayAdapter(context,R.layout.spinner_custom_layout,spnList)
        spnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnStudentCourses.adapter = spnAdapter

        spnPosition = spnStudentCourses.selectedItemPosition
        spnStudentCourses.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spnPosition = position
            }

        }

        btnStudentCoursesSearch.setOnClickListener {
            list = ArrayList()
            list = teacherClassDAO.getAllTeacherClass(spnList!!.get(spnStudentCourses.selectedItemPosition))
            adapter = ClassesParticipateAdapter(context!!, this,list!!)
            rvClassesParticipate.layoutManager = LinearLayoutManager(context)
            rvClassesParticipate.adapter = adapter
        }

        return rootview
    }

    fun participateClass(aPosition: Int){
        var studentClassDAO = StudentClassDAO(context!!)

        var cl = list!!.get(aPosition)
        var sharedPreferences = context!!.getSharedPreferences("STUDENT",Context.MODE_PRIVATE)

        var sID = sharedPreferences.getString("studentID","")

        var cSeason = spnList!!.get(spnPosition!!)
        var clName = cl.name
        var isJoined = studentClassDAO.checkJoinedClasses(sID,cSeason,clName!!)

        if(isJoined == true){
            var clID = cl.id
            var clRemainAmount = cl.amount - 1
            list!!.set(aPosition,TeacherClass(cSeason,cl.id,clName,cl.schedule,cl.examDate,clRemainAmount))
            studentClassDAO.insertJoinedClasses(JoinedClass(null,cSeason,sID,clName))
            studentClassDAO.updateAmountForClasses(clID!!, TeacherClass(null,null,null,null,null,clRemainAmount))
            Toast.makeText(context,resources.getString(R.string.error_class_signup),Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context,resources.getString(R.string.error_class_signup_error),Toast.LENGTH_SHORT).show()
        }

    }

    fun lookingForRemainSlot(position: Int){
        var cl = list!!.get(position)
        var remainSlot = cl.amount

        var alertDialog = AlertDialog.Builder(context)
        alertDialog.setIcon(R.drawable.img_find)
        alertDialog.setTitle(R.string.title_dialog_amount)
        alertDialog.setMessage(resources.getString(R.string.title_dialog_amount_message) +" "+ remainSlot +" "+resources.getString(R.string.Slot))
        alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
        var dialog = alertDialog.create()
        dialog.show()
    }

}