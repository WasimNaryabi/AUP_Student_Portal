package xyz.computingabc.ibmsprotal.Student.Quiz.Api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import xyz.computingabc.ibmsprotal.Student.Quiz.Data.QuizMarksResponse

interface Api {
    @FormUrlEncoded
    @POST("cheking-quiz-marks-api.php")
    fun fetchQuizMarks(
        @Field("course_code") course_code:String,
        @Field("history_id") history_id:String
    ): Call<QuizMarksResponse>
}