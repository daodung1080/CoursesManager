package com.dung.mob201_assignment.DAO

import android.content.ContentValues
import android.content.Context
import com.dung.mob201_assignment.Model.TeacherClass
import com.dung.mob201_assignment.Model.TeacherCourses
import com.dung.mob201_assignment.SQLite.Constants
import com.dung.mob201_assignment.SQLite.Database

class TeacherClassDAO(context: Context) {

    var database = Database(context)

    /// Insert, Update, Remove

    fun insertTeacherClass(teacherClass: TeacherClass): Long{
        var result: Long = -1

        var contentValues = ContentValues()
        contentValues.put(Constants().class_courses_season, teacherClass.season)
        contentValues.put(Constants().class_id, teacherClass.id)
        contentValues.put(Constants().class_name, teacherClass.name)
        contentValues.put(Constants().class_schedule, teacherClass.schedule)
        contentValues.put(Constants().class_exam, teacherClass.examDate)

        var sqLiteDatabase = database.writableDatabase
        result = sqLiteDatabase.insert(Constants().class_table, null , contentValues)
        sqLiteDatabase.close()

        return result
    }

    /// Get data from table

    fun getAllTeacherClass(): ArrayList<TeacherClass>{
        var list: ArrayList<TeacherClass> = ArrayList()

        var query = "select * from "+ Constants().class_table
        var sqLiteDatabase = database.readableDatabase
        var cursor = sqLiteDatabase.rawQuery(query,null)
        if(cursor != null){
            if(cursor.count > 0){
                while (cursor.moveToNext()){
                    var clID = cursor.getInt(cursor.getColumnIndex(Constants().class_id))
                    var clSeason = cursor.getString(cursor.getColumnIndex(Constants().class_courses_season))
                    var clName = cursor.getString(cursor.getColumnIndex(Constants().class_name))
                    var clSchedule = cursor.getString(cursor.getColumnIndex(Constants().class_schedule))
                    var clExam = cursor.getString(cursor.getColumnIndex(Constants().class_exam))

                    var teacherClass = TeacherClass(clSeason,clID,clName,clSchedule,clExam)
                    list.add(teacherClass)

                }
            }
        }

        return list
    }

}