package com.dung.mob201_assignment.teacher.teacherfragment

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dung.mob201_assignment.R
import kotlinx.android.synthetic.main.fragment_teacher_insert_courses.view.*
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.widget.*
import com.dung.mob201_assignment.dao.TeacherCousesDAO
import com.dung.mob201_assignment.model.TeacherCourses
import kotlinx.android.synthetic.main.dialog_pick_up_season.view.*
import java.lang.NumberFormatException
import java.util.*


class InsertCoursesFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootview = inflater.inflate(R.layout.fragment_teacher_insert_courses,container,false)

        var tilCoursesName = rootview.tilCoursesName
        var edtCoursesName = rootview.edtCoursesName
        var txtPickDate = rootview.txtPickDate
        var btnPickDateCourses = rootview.btnPickDateCourses
        var btnInsertCourses = rootview.btnInsertCourses

        var teacherCousesDAO = TeacherCousesDAO(context!!)

        btnPickDateCourses.setOnClickListener {
            var alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle(resources.getString(R.string.title_teacher_season_pick))
            alertDialog.setIcon(R.drawable.img_course)
            var view = layoutInflater.inflate(R.layout.dialog_pick_up_season,null)

            var spnYearPick = view.spnYearPick
            var spnSeasonPick = view.spnSeasonPick
            var listYear: List<String> = listOf("2018","2019","2020","2021","2022",
                "2023","2024","2025","2026","2027",
                "2028","2029","2030","2031","2032")
            var listSeason = listOf<String>("Spring and Summer","Summer and Fall",
                "Fall and Winter","Winter and Spring")

            var yearAdapter = ArrayAdapter<String>(context,R.layout.spinner_custom_layout,listYear)
            yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            var seasonAdapter = ArrayAdapter<String>(context,R.layout.spinner_custom_layout,listSeason)
            seasonAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spnYearPick.adapter = yearAdapter
            spnSeasonPick.adapter = seasonAdapter

            alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { _ , _ ->
                var year = listYear.get(spnYearPick.selectedItemPosition)
                var season = listSeason.get(spnSeasonPick.selectedItemPosition)
                txtPickDate.setText("$year $season")
            })

            alertDialog.setView(view)
            var dialog = alertDialog.create()
            dialog.show()
        }

        btnInsertCourses.setOnClickListener {

                var cName = edtCoursesName.text.toString()
                var cSeason = txtPickDate.text.toString()

                if(cName.length < 5){
                    tilCoursesName.error = (resources.getString(R.string.error_courses_name))
                }
                else if(cSeason.equals(resources.getString(R.string.edtPickDateCourses))){
                    showMessage(resources.getString(R.string.error_courses_season))
                }
                else if(teacherCousesDAO.insertTeacherCourses(TeacherCourses(cName,cSeason)) > 0){
                    showMessage(resources.getString(R.string.error_courses_done))
                    tilCoursesName.error =null
                    edtCoursesName.setText("")
                    txtPickDate.setText(resources.getString(R.string.edtPickDateCourses))
                }
                else if(teacherCousesDAO.insertTeacherCourses(TeacherCourses(cName,cSeason)) <= 0){
                    tilCoursesName.error =null
                    showMessage(resources.getString(R.string.error_courses_cancel))
                }

        }

        return rootview
    }

    fun showMessage(message: String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}