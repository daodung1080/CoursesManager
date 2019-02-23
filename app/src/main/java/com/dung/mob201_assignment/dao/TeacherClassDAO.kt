package com.dung.mob201_assignment.dao

import android.content.ContentValues
import android.content.Context
import com.dung.mob201_assignment.model.ClassInformation
import com.dung.mob201_assignment.model.TeacherClass
import com.dung.mob201_assignment.sqlite.Constants
import com.dung.mob201_assignment.sqlite.Database

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
        contentValues.put(Constants().class_amount, teacherClass.amount)

        var sqLiteDatabase = database.writableDatabase
        result = sqLiteDatabase.insert(Constants().class_table, null , contentValues)
        sqLiteDatabase.close()

        return result
    }

    fun removeTeacherClass(clID: Int, clSeason: String,switch: Int): Long{
        var result: Long = -1
        var sqLiteDatabase = database.writableDatabase

        if (switch == 1){
            result = sqLiteDatabase.delete(Constants().class_table,
                Constants().class_id+"=?", arrayOf(clID.toString())).toLong()
        }else if(switch == 2){
            result = sqLiteDatabase.delete(Constants().class_table,
                Constants().class_courses_season+"=?", arrayOf(clSeason)).toLong()
        }
        sqLiteDatabase.close()

        return result
    }

    /// Get data from table

    fun getAllTeacherClass(coursesSeason: String): ArrayList<TeacherClass>{
        var list: ArrayList<TeacherClass> = ArrayList()

        var classTable = Constants().class_table
        var classSeason = Constants().class_courses_season

        var query = "select * from $classTable where $classSeason like '$coursesSeason' "

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
                    var clAmount = cursor.getInt(cursor.getColumnIndex(Constants().class_amount))

                    var teacherClass = TeacherClass(clSeason,clID,clName,clSchedule,clExam,clAmount)
                    list.add(teacherClass)

                }
                cursor.close()
                sqLiteDatabase.close()
            }
        }

        return list
    }

}