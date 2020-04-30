package xyz.computingabc.ibmsprotal.Student.Quiz.Data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class QuizMarks (
    @SerializedName("quiz_no")
    @Expose
    val QuizNo: String,
    @SerializedName("topic")
    @Expose
    val QuizTopic: String,
    @SerializedName("marks_obtained")
    @Expose
    val Marks: String

)