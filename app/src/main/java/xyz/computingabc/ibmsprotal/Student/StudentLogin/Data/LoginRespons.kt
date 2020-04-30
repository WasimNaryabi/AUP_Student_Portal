package xyz.computingabc.ibmsprotal.Student.StudentLogin.Data

import xyz.computingabc.ibmsprotal.Student.StudentLogin.Data.Student

data class LoginRespons (
    val error:Boolean,
    val message:String,
    val student: Student
)