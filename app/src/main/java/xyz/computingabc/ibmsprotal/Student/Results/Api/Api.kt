package xyz.computingabc.ibmsprotal.Student.Results.Api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import xyz.computingabc.ibmsprotal.Student.Results.Data.FinalMarksRespons
import xyz.computingabc.ibmsprotal.Student.Results.Data.MidMarksRespons

interface Api {

    @FormUrlEncoded
    @POST("cheking-mid-marks-api.php")
    fun fetchMidMarks(
        @Field("history_id") history_id:String,
        @Field("discipline_id") discipline_id:String,
        @Field("semester_id") semester_id:String
    ): Call<MidMarksRespons>

    @FormUrlEncoded
    @POST("cheking-final-marks-api.php")
    fun fetchFinalMarks(
        @Field("student_history_id") student_history_id:String,
        @Field("discipline_id") discipline_id:String,
        @Field("semester_id") semester_id:String
    ): Call<FinalMarksRespons>
}