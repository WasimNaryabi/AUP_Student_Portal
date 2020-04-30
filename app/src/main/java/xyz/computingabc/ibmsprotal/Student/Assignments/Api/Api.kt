package xyz.computingabc.ibmsprotal.Student.Assignments.Api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import xyz.computingabc.ibmsprotal.Student.Assignments.Data.AssignmentMarksResponse

interface Api {

    @FormUrlEncoded
    @POST("cheking-assignments-marks-api.php")
    fun fetchAssigmnetMarks(
        @Field("course_code") course_code:String,
        @Field("history_id") history_id:String
    ): Call<AssignmentMarksResponse>
}