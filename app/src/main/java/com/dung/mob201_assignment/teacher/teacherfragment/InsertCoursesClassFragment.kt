package com.dung.mob201_assignment.teacher.teacherfragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.dung.mob201_assignment.dao.TeacherClassDAO
import com.dung.mob201_assignment.model.TeacherClass
import com.dung.mob201_assignment.R
import kotlinx.android.synthetic.main.fragment_teacher_insert_courses_class.view.*
import android.app.DatePickerDialog
import com.dung.mob201_assignment.dao.TeacherCousesDAO
import kotlinx.android.synthetic.main.fragment_teacher_insert_courses_class.*
import java.lang.NumberFormatException
import java.util.*


class InsertCoursesClassFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootview = inflater.inflate(R.layout.fragment_teacher_insert_courses_class,container,false)

        // Edt Init
        var edtPickNumberOfDay = rootview.edtPickNumberOfDay
        var edtClassName = rootview.edtClassName
        var edtPickDateExam = rootview.edtPickDateExam
        var editClassAmount = rootview.edtClassAmount
        var tilClassName = rootview.tilClassName
        var tilClassAmount = rootview.tilClassAmount

        // Btn Init
        var btnInsertClass = rootview.btnInsertClass
        var btnPickDateExam = rootview.btnPickDateExam
        var btnPickNumberOfDay = rootview.btnPickNumberOfDay

        // Spn Init
        var spnTeacherCoursesSeason = rootview.spnTeacherCoursesSeason

        var teacherCousesDAO = TeacherCousesDAO(context!!)
        var teacherClassDAO = TeacherClassDAO(context!!)

        var spnList: ArrayList<String> = ArrayList()
        spnList = teacherCousesDAO.getAllCoursesSeason()

        var spnAdapter: ArrayAdapter<String> = ArrayAdapter(context,R.layout.spinner_custom_layout,spnList)
        spnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnTeacherCoursesSeason.adapter = spnAdapter

        btnPickDateExam.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(context,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    var monthInt = monthOfYear + 1
                    var months = "$monthInt"
                    if(monthInt < 10){
                        months = "0$monthInt"
                    }
                    if(monthInt == 13){
                        monthInt = 12
                    }
                    var days = "$dayOfMonth"
                    if(dayOfMonth < 10){
                        days = "0$dayOfMonth"
                    }
                    edtPickDateExam.setText("$year-$months-$days")
                }, year, month, day
            )
            datePickerDialog.show()
        }
        btnPickNumberOfDay.setOnClickListener {
            var arrayDay = arrayOf(
                resources.getString(R.string.Monday),resources.getString(R.string.Tuesday),
                resources.getString(R.string.Wednesday),resources.getString(R.string.Thursday),
                resources.getString(R.string.Friday),resources.getString(R.string.Saturday),
                resources.getString(R.string.Sunday))
            var arrayChecked = booleanArrayOf(false,false,false,false,false,false,false)

            var alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
            alertDialog.setTitle(resources.getString(R.string.edtPickNumberOfDay))

            alertDialog.setMultiChoiceItems(arrayDay,arrayChecked,
                DialogInterface.OnMultiChoiceClickListener { dialog, which, isChecked ->
                    arrayChecked[which] = isChecked
                })
            alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { _ , _ ->

                var text = ""
                for(i in 0..arrayDay.size - 1){
                    var checked = arrayChecked[i]
                    if(checked){
                        var day = arrayDay[i]
                        text += "$day "
                    }
                }
                if(text.equals("")){
                    text = resources.getString(R.string.edtPickNumberOfDay)
                }
                edtPickNumberOfDay.setText(text)

            })

            var dialog = alertDialog.create()
            dialog.show()

        }
        btnInsertClass.setOnClickListener {
            try{
                var clCoursesSeason = spnList.get(spnTeacherCoursesSeason.selectedItemPosition)
                var clName = edtClassName.text.toString()
                var clSchedule = edtPickNumberOfDay.text.toString()
                var clExam = edtPickDateExam.text.toString()
                var clAmount = (edtClassAmount.text.toString()).toInt()
                var teacherClass = TeacherClass(clCoursesSeason,null,clName,clSchedule,clExam,clAmount)

                if(clName.length < 5){
                    tilClassName.error = resources.getString(R.string.error_class_name)
                }else if(clSchedule.equals(resources.getString(R.string.edtPickNumberOfDay))){
                    showMessage(resources.getString(R.string.error_class_schedule))
                }else if(clExam.equals(resources.getString(R.string.edtPickDateExam))){
                    showMessage(resources.getString(R.string.error_class_exam))
                }else if(clAmount <= 0){
                    tilClassAmount.error = resources.getString(R.string.error_class_amount_format)
                }else if(teacherClassDAO.insertTeacherClass(teacherClass) > 0){
                    showMessage(resources.getString(R.string.error_class_done))
                    edtClassName.setText("")
                    edtClassAmount.setText("")
                    edtPickNumberOfDay.setText(resources.getString(R.string.edtPickNumberOfDay))
                    edtPickDateExam.setText(resources.getString(R.string.edtPickDateExam))
                    tilClassName.error = null
                    tilClassName.error = null
                    tilClassAmount.error = null
                } else if(teacherClassDAO.insertTeacherClass(teacherClass) <= 0){
                    showMessage(resources.getString(R.string.error_class_cancel))
                    tilClassName.error = null
                    tilClassAmount.error = null
                }
            }catch (e: ArrayIndexOutOfBoundsException){
                showMessage(resources.getString(R.string.error_class_format))
            }catch (e: NumberFormatException){
                showMessage(resources.getString(R.string.error_class_amount_format))
            }
        }

        return rootview
    }

    fun showMessage(message: String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

}