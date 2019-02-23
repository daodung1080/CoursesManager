package com.dung.mob201_assignment.dao

import android.content.ContentValues
import android.content.Context
import com.dung.mob201_assignment.model.TeacherCourses
import com.dung.mob201_assignment.sqlite.Constants
import com.dung.mob201_assignment.sqlite.Database

class TeacherCousesDAO(context: Context) {

    var database = Database(context)

    /// Insert, Update, Remove

    fun insertTeacherCourses(teacherCourses: TeacherCourses): Long{
        var result: Long = -1

        var contentValues = ContentValues()
        contentValues.put(Constants().courses_season, teacherCourses.season)
        contentValues.put(Constants().courses_name, teacherCourses.name)

        var sqLiteDatabase = database.writableDatabase
        result = sqLiteDatabase.insert(Constants().courses_table, null , contentValues)
        sqLiteDatabase.close()

        return result
    }

    fun removeTeacherCourses(cSeason: String): Long{
        var result: Long = -1

        var sqLiteDatabase = database.writableDatabase
        result = sqLiteDatabase.delete(Constants().courses_table,Constants().courses_season+"=?",
            arrayOf(cSeason)).toLong()
        sqLiteDatabase.close()

        return result
    }

    /// Get data from table

    fun getAllTeacherCourses(): ArrayList<TeacherCourses>{
        var list: ArrayList<TeacherCourses> = ArrayList()

        var query = "select * from "+Constants().courses_table
        var sqLiteDatabase = database.readableDatabase
        var cursor = sqLiteDatabase.rawQuery(query,null)
        if(cursor != null){
            if(cursor.count > 0){
                while (cursor.moveToNext()){
                    var cName = cursor.getString(cursor.getColumnIndex(Constants().courses_name))
                    var cSeason = cursor.getString(cursor.getColumnIndex(Constants().courses_season))

                    var teacherCourses = TeacherCourses(cName,cSeason)
                    list.add(teacherCourses)

                }
                cursor.close()
                sqLiteDatabase.close()
            }
        }

        return list
    }

    fun getAllCoursesSeason(): ArrayList<String>{
        var list: ArrayList<String> = ArrayList()
        var query = "select * from "+ Constants().courses_table
        var sqLiteDatabase = database.readableDatabase
        var cursor = sqLiteDatabase.rawQuery(query,null)
        if(cursor != null){
            if(cursor.count > 0){
                while (cursor.moveToNext()){
                    var coursesSeason = cursor.getString(cursor.getColumnIndex(Constants().courses_season))
                    list.add(coursesSeason)
                }
                cursor.close()
                sqLiteDatabase.close()
            }
        }
        return list
    }

}