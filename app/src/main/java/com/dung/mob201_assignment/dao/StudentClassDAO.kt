package com.dung.mob201_assignment.dao

import android.content.ContentValues
import android.content.Context
import com.dung.mob201_assignment.model.ClassInformation
import com.dung.mob201_assignment.model.JoinedClass
import com.dung.mob201_assignment.model.Student
import com.dung.mob201_assignment.model.TeacherClass
import com.dung.mob201_assignment.sqlite.Constants
import com.dung.mob201_assignment.sqlite.Database
import java.text.SimpleDateFormat

class StudentClassDAO(context: Context) {

    var database = Database(context)

    /// Insert, Update, Remove

    fun insertJoinedClasses(joinedClass: JoinedClass): Long{
        var result: Long = -1

        var contentValues = ContentValues()
        contentValues.put(Constants().class_joined_id, joinedClass.id)
        contentValues.put(Constants().class_joined_courses_season, joinedClass.season)
        contentValues.put(Constants().class_joined_student_id, joinedClass.studentID)
        contentValues.put(Constants().class_joined_name, joinedClass.className)

        var sqLiteDatabase = database.writableDatabase
        result = sqLiteDatabase.insert(Constants().class_joined_table, null , contentValues)
        sqLiteDatabase.close()

        return result
    }

    fun updateAmountForClasses(id: Int,teacherClass: TeacherClass): Long{
        var result: Long = -1

        var contentValues = ContentValues()
        contentValues.put(Constants().class_amount, teacherClass.amount)

        var sqLiteDatabase = database.writableDatabase
        result = sqLiteDatabase.update(Constants().class_table,contentValues,
            Constants().class_id+"=?", arrayOf(id.toString())).toLong()
        sqLiteDatabase.close()

        return result
    }

    fun removeJoinedClasses(id: Int): Long{
        var result: Long = -1

        var sqLiteDatabase = database.writableDatabase
        result = sqLiteDatabase.delete(Constants().class_joined_table,Constants().class_joined_id+"=?"
            , arrayOf(id.toString())).toLong()

        sqLiteDatabase.close()

        return result
    }

    /// Get data from table

    fun checkJoinedClasses(studentID: String?, coursesSeason: String, className: String): Boolean{
        var clJoinedTable = Constants().class_joined_table
        var sID = Constants().class_joined_student_id
        var cSeason = Constants().class_joined_courses_season
        var clName = Constants().class_joined_name

        var result = true
        var query = "select * from $clJoinedTable where $sID = '$studentID'" +
                "and $cSeason = '$coursesSeason' and $clName = '$className' "

        var sqLiteDatabase = database.readableDatabase
        var cursor = sqLiteDatabase.rawQuery(query,null)

        if(cursor.count > 0){
            result = false
        }

        return result
    }

    fun getAllClassJoined(studentID: String): ArrayList<JoinedClass>{
        var list: ArrayList<JoinedClass> = ArrayList()

        var cljoinedTable = Constants().class_joined_table
        var stID = Constants().class_joined_student_id

        var query = "select * from $cljoinedTable where $stID = '$studentID' "
        var sqLiteDatabase = database.readableDatabase
        var cursor = sqLiteDatabase.rawQuery(query,null)
        if(cursor != null){
            if(cursor.count > 0){
                while (cursor.moveToNext()){
                    var clID = cursor.getInt(cursor.getColumnIndex(Constants().class_joined_id))
                    var season = cursor.getString(cursor.getColumnIndex(Constants().class_joined_courses_season))
                    var clName = cursor.getString(cursor.getColumnIndex(Constants().class_joined_name))
                    var studentID = cursor.getString(cursor.getColumnIndex(Constants().class_joined_student_id))
                    list.add(JoinedClass(clID,season,studentID,clName))
                }
                cursor.close()
                sqLiteDatabase.close()
            }
        }

        return list
    }

    fun getAllClasses(className: String): ArrayList<ClassInformation>{
        var list: ArrayList<ClassInformation> = ArrayList()

        var clTables = Constants().class_table
        var clNames = Constants().class_name

        var query = "select * from $clTables where $clNames like '$className' "
        var sqLiteDatabase = database.readableDatabase
        var cursor = sqLiteDatabase.rawQuery(query,null)
        if(cursor != null){
            if(cursor.count > 0){
                while (cursor.moveToNext()){
                    var clName = cursor.getString(cursor.getColumnIndex(Constants().class_name))
                    var clSchedule = cursor.getString(cursor.getColumnIndex(Constants().class_schedule))
                    var clExam = cursor.getString(cursor.getColumnIndex(Constants().class_exam))
                    var clSeason = cursor.getString(cursor.getColumnIndex(Constants().class_courses_season))

                    list.add(ClassInformation(clSchedule,clExam,clName,clSeason))
                }
                cursor.close()
                sqLiteDatabase.close()
            }
        }

        return list
    }

    fun checkClassessName(className: String): Boolean{
        var result = false
        var clTables = Constants().class_table
        var clName = Constants().class_name

        var query = "select * from $clTables where $clName like '$className' "
        var sqLiteDatabase = database.readableDatabase
        var cursor = sqLiteDatabase.rawQuery(query,null)

        if(cursor.count > 0){
            result = true
        }

        return result
    }

}