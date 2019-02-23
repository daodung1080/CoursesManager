package com.dung.mob201_assignment.dao

import android.content.ContentValues
import android.content.Context
import com.dung.mob201_assignment.model.Student
import com.dung.mob201_assignment.sqlite.Constants
import com.dung.mob201_assignment.sqlite.Database

class StudentDAO(context: Context) {

    var database = Database(context)

    /// Insert, Update, Remove

    fun insertStudent(student: Student): Long{
        var result: Long = -1

        var contentValues = ContentValues()
        contentValues.put(Constants().student_id, student.id)
        contentValues.put(Constants().student_fullname, student.fullname)
        contentValues.put(Constants().student_password, student.password)

        var sqLiteDatabase = database.writableDatabase
        result = sqLiteDatabase.insert(Constants().student_table, null , contentValues)
        sqLiteDatabase.close()

        return result
    }

    fun removeStudent(sID: String): Long{
        var result: Long = -1

        var sqLiteDatabase = database.writableDatabase
        result = sqLiteDatabase.delete(Constants().student_table,Constants().student_id+"=?",
            arrayOf(sID)).toLong()
        sqLiteDatabase.close()

        return result
    }

    /// Get data from table

    fun checkStudentLogin(studentID: String, password: String): Boolean{
        var result = false
        var sTable = Constants().student_table
        var sID = Constants().student_id
        var sPassword = Constants().student_password
        var query = "select * from $sTable where $sID like '$studentID' and $sPassword like '$password' "

        var sqLiteDatabase = database.readableDatabase
        var cursor = sqLiteDatabase.rawQuery(query,null)
        if(cursor.count > 0){
            result = true
        }

        return result
    }

    fun getAllStudent(): ArrayList<Student>{
        var list: ArrayList<Student> = ArrayList()

        var query = "select * from "+ Constants().student_table
        var sqLiteDatabase = database.readableDatabase
        var cursor = sqLiteDatabase.rawQuery(query,null)
        if(cursor != null){
            if(cursor.count > 0){
                while (cursor.moveToNext()){
                    var sID = cursor.getString(cursor.getColumnIndex(Constants().student_id))
                    var sFullName = cursor.getString(cursor.getColumnIndex(Constants().student_fullname))
                    var sPassword = cursor.getString(cursor.getColumnIndex(Constants().student_password))

                    var student = Student(sID,sFullName,sPassword)
                    list.add(student)

                }
                cursor.close()
                sqLiteDatabase.close()
            }
        }

        return list
    }

}