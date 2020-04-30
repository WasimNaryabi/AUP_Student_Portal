package xyz.computingabc.ibmsprotal.Student.Lectures.Api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import xyz.computingabc.ibmsprotal.Student.Lectures.Data.LecturesResponse
import xyz.computingabc.ibmsprotal.Student.Lectures.Data.SubjectResponse

interface Api {

    @FormUrlEncoded
    @POST("student_subjects-show-api.php")
    fun fatchSubjects(
        @Field("discipline_id") discipline_id:String,
        @Field("semester_id") semester_id:String
    ): Call<SubjectResponse>

    @FormUrlEncoded
    @POST("teacher-uploaded-lectures.php")
    fun fatchLectures(
        @Field("course_code") course_code:String
    ): Call<LecturesResponse>
}