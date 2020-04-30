package xyz.computingabc.ibmsprotal.Student.Attendance.Model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.student_attendance_layout.view.*
import xyz.computingabc.ibmsprotal.R
import xyz.computingabc.ibmsprotal.Student.Attendance.Data.StudentAttendance
import xyz.computingabc.ibmsprotal.Student.Attendance.StudentSubjectAttendanceList

class StudentAttendanceAdopter (val Attendance:List<StudentAttendance>) : RecyclerView.Adapter<StudentAttendanceAdopter.MovieViewHolder>() {

    // This funtion is used for attach layout to adopter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.student_attendance_layout,parent,false)
        )
    }

    // This funtion is used for finding size of list
    override fun getItemCount()=Attendance.size

    // This funtion is used for binding the data
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val Attendance= Attendance[position]
        val context = holder.view.context
        val activity=context as StudentSubjectAttendanceList
        holder.view.date.text=Attendance.Date
        holder.view.topic.text=Attendance.Topic
        if(Attendance.State.equals("1")){
            holder.view.studentState.setImageResource(R.drawable.ic_present)
        }else{
            holder.view.studentState.setImageResource(R.drawable.ic_absent)
        }



    }

    class MovieViewHolder(val view: View):RecyclerView.ViewHolder(view)

}