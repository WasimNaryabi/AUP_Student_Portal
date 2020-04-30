package xyz.computingabc.ibmsprotal.Student.TimeTable.Api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import xyz.computingabc.ibmsprotal.Student.TimeTable.Data.TimeTableResponse

interface Api {
    @FormUrlEncoded
    @POST("student-timetable-api.php")
    fun fetchTimetable(
        @Field("discipline_id") discipline_id:String,
        @Field("session_id") session_id:String,
        @Field("semester_id") semester_id:String,
        @Field("section_id") section_id:String,
        @Field("day_id") day_id:String

    ): Call<TimeTableResponse>
}