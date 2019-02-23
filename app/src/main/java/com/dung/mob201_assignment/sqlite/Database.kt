package com.dung.mob201_assignment.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context?) :
    SQLiteOpenHelper(context, "mob201Assignment5.sql", null,1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Constants().create_courses_table)
        db?.execSQL(Constants().create_class_table)
        db?.execSQL(Constants().create_student_table)
        db?.execSQL(Constants().create_class_joined_table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists "+Constants().courses_table)
        db?.execSQL("drop table if exists "+Constants().class_table)
        db?.execSQL("drop table if exists "+Constants().student_table)
        db?.execSQL("drop table if exists "+Constants().class_joined_table)
        onCreate(db)
    }
}