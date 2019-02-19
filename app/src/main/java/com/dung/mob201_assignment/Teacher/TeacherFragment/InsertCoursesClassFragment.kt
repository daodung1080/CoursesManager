package com.dung.mob201_assignment.Teacher.TeacherFragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.dung.mob201_assignment.R
import kotlinx.android.synthetic.main.fragment_teacher_insert_courses_class.view.*

class InsertCoursesClassFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootview = inflater.inflate(R.layout.fragment_teacher_insert_courses_class,container,false)

        var edtPickNumberOfDay: EditText = rootview.edtPickNumberOfDay
        var btnPickNumberOfDay: Button = rootview.btnPickNumberOfDay

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

        return rootview
    }

}