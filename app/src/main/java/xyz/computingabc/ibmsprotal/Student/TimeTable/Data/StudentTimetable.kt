package xyz.computingabc.ibmsprotal.Student.TimeTable.Data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StudentTimetable (
    @SerializedName("title_course")
    @Expose
    val CourseTitle: String,
    @SerializedName("room")
    @Expose
    val Room: String,
    @SerializedName("teacher")
    @Expose
    val Teacher:String,
    @SerializedName("time")
    @Expose
    val Time: String


)