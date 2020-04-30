package xyz.computingabc.ibmsprotal.Student.Results.Data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FinalMarks (
    @SerializedName("final_marks")
    @Expose
    val final_marks: String,
    @SerializedName("gpa")
    @Expose
    val Subject_GPA: String,
    @SerializedName("subject")
    @Expose
    val Subject: String,
    @SerializedName("total_gpa")
    @Expose
    val Total_GPA: String,
    @SerializedName("total_marks")
    @Expose
    val Total: String
)