package com.dung.mob201_assignment.SQLite

class Constants {

    /// table
    var courses_table = "courses"
    var class_table = "class"
    var student_table = "student"

    // column
    var courses_season = "season"
    var courses_name = "name"
    var courses_amount = "amount"

    var class_id = "id"
    var class_courses_season = "season"
    var class_name = "name"
    var class_schedule = "schedule"
    var class_exam = "exam"

    var student_id = "studentid"
    var student_fullname = "fullname"
    var student_password = "password"

    // create table
    var create_courses_table = "create table $courses_table ($courses_season nvarchar(50) not null primary key," +
            "$courses_name nvarchar(50), $courses_amount integer)"

    var create_class_table = "create table $class_table ($class_id integer primary key autoincrement, " +
            "$class_courses_season nvarchar(50) not null, $class_name nvarchar(50) not null, " +
            "$class_schedule nvarchar(50), $class_exam date)"

    var create_student_table = "create table $student_table ($student_id nvarchar(50) not null primary key, " +
            "$student_fullname nvarchar(50), $student_password nvarchar(50) not null) "

}