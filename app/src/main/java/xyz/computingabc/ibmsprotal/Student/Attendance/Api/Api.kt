package xyz.computingabc.ibmsprotal.Student.Attendance.Api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import xyz.computingabc.ibmsprotal.Student.Attendance.Data.AttendanceResponse
import xyz.computingabc.ibmsprotal.Student.Lectures.Data.SubjectResponse

interface Api {
    @FormUrlEncoded
    @POST("student-attendance-show-api.php")
    fun fetchAttendance(
        @Field("course_code") course_code:String,
        @Field("history_id") history_id:String
    ): Call<AttendanceResponse>
}