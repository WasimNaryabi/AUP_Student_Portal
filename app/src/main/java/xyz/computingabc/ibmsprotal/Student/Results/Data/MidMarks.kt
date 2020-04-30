package xyz.computingabc.ibmsprotal.Student.Results.Data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MidMarks (
    @SerializedName("quiz_marks")
    @Expose
    val QuizMarks: String,
    @SerializedName("assignment")
    @Expose
    val AssaignmentMarks: String,
    @SerializedName("midmarks")
    @Expose
    val MidMarks: String,
    @SerializedName("subject")
    @Expose
    val Subject: String,
    @SerializedName("midterm_marks")
    @Expose
    val Marks: String,
    @SerializedName("total")
    @Expose
    val Total: String

)