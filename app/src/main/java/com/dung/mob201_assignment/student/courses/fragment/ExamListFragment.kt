package com.dung.mob201_assignment.student.courses.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import com.dung.mob201_assignment.R
import com.dung.mob201_assignment.adapter.ExamAdapter
import com.dung.mob201_assignment.dao.StudentClassDAO
import com.dung.mob201_assignment.dao.TeacherCousesDAO
import com.dung.mob201_assignment.model.ClassInformation
import kotlinx.android.synthetic.main.fragment_student_exam.view.*

class ExamListFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootview = inflater.inflate(R.layout.fragment_student_exam,container,false)

        var edtExam = rootview.edtExam
        var rvExam = rootview.rvExam
        var btnExam = rootview.btnExam
        var tilExam = rootview.tilExam

        btnExam.setOnClickListener {
            var clName = edtExam.text.toString()
            var studentClassDAO = StudentClassDAO(context!!)
            var isAClass = studentClassDAO.checkClassessName(clName)
            if(isAClass == true){
                if(clName.length < 5){
                    tilExam.error = resources.getString(R.string.error_class_name)
                }
                else{
                    tilExam.error = null
                    var list: ArrayList<ClassInformation> = ArrayList()
                    var studentClassDAO = StudentClassDAO(context!!)
                    list = studentClassDAO.getAllClasses(clName)
                    var adapter = ExamAdapter(context!!,list)

                    rvExam.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                    rvExam.adapter = adapter
                }
            }else{
                Toast.makeText(context,resources.getString(R.string.error_class_insert),Toast.LENGTH_SHORT).show()
            }
        }

        return rootview
    }

}