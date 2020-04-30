package xyz.computingabc.ibmsprotal.Student.Assignments.Data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AssignmentMarks (
    @SerializedName("ass_no")
    @Expose
    val AssNo: String,
    @SerializedName("topic")
    @Expose
    val AssTopic: String,
    @SerializedName("marks_obtained")
    @Expose
    val Marks: String

)