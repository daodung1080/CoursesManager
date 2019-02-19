package com.dung.mob201_assignment.Teacher.TeacherFragment

import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dung.mob201_assignment.R
import kotlinx.android.synthetic.main.fragment_teacher_insert_courses.view.*
import android.app.DatePickerDialog
import android.widget.*
import com.dung.mob201_assignment.DAO.TeacherCousesDAO
import com.dung.mob201_assignment.Model.TeacherCourses
import java.lang.NumberFormatException
import java.util.*


class InsertCoursesFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootview = inflater.inflate(R.layout.fragment_teacher_insert_courses,container,false)

        var tilCoursesName = rootview.tilCoursesName
        var tilCoursesAmount = rootview.tilCoursesAmount
        var edtCoursesName = rootview.edtCoursesName
        var edtCoursesAmount = rootview.edtCoursesAmount
        var txtPickDate = rootview.txtPickDate
        var btnPickDateCourses = rootview.btnPickDateCourses
        var btnInsertCourses = rootview.btnInsertCourses

        var teacherCousesDAO = TeacherCousesDAO(context!!)

        btnPickDateCourses.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(context,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    var monthInt = monthOfYear + 1
                    var months = "$monthInt"
                    if(monthInt > 0 && monthInt <= 4){
                        months = "Spring and Summer"
                    }
                    else if(monthInt > 4 && monthInt <= 8){
                        months = "Summer and Fall"
                    }
                    else if(monthInt > 8 && monthInt <= 12){
                        months = "Fall and Winter"
                    }
                    txtPickDate.setText("$year $months")
                }, year, month, day
            )
            datePickerDialog.show()
        }

        btnInsertCourses.setOnClickListener {

            try {
                var cName = edtCoursesName.text.toString()
                var cSeason = txtPickDate.text.toString()
                var cAmount = (edtCoursesAmount.text.toString()).toInt()

                if(cName.length < 5){
                    tilCoursesName.error = (resources.getString(R.string.error_courses_name))
                }
                else if(cSeason.equals(resources.getString(R.string.edtPickDateCourses))){
                    showMessage(resources.getString(R.string.error_courses_season))
                }
                else if(cAmount <= 0){
                    tilCoursesAmount.error = (resources.getString(R.string.error_courses_amount))
                }
                else if(teacherCousesDAO.insertTeacherCourses(TeacherCourses(cName,cSeason,cAmount)) > 0){
                    showMessage(resources.getString(R.string.error_courses_done))
                    tilCoursesAmount.error = null
                    tilCoursesName.error =null
                    edtCoursesAmount.setText("")
                    edtCoursesName.setText("")
                    txtPickDate.setText(resources.getString(R.string.edtPickDateCourses))
                }
                else if(teacherCousesDAO.insertTeacherCourses(TeacherCourses(cName,cSeason,cAmount)) <= 0){
                    tilCoursesAmount.error = null
                    tilCoursesName.error =null
                    showMessage(resources.getString(R.string.error_courses_cancel))
                }
            }catch (e: NumberFormatException){
                showMessage(resources.getString(R.string.error_courses_amount_format))
            }

        }

        return rootview
    }

    fun showMessage(message: String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}