package xyz.computingabc.ibmsprotal.Student.TimeTable.Model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.lectures_list_layout.view.*
import kotlinx.android.synthetic.main.student_timetable_layout.view.*
import xyz.computingabc.ibmsprotal.R
import xyz.computingabc.ibmsprotal.Student.TimeTable.Data.StudentTimetable


class StudentMondayTimeTableAdopter (val Classes:List<StudentTimetable>) : RecyclerView.Adapter<StudentMondayTimeTableAdopter.MovieViewHolder>() {

    // This funtion is used for attach layout to adopter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.student_timetable_layout,parent,false)
        )
    }

    // This funtion is used for finding size of list
    override fun getItemCount()=Classes.size

    // This funtion is used for binding the data
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val Class= Classes[position]

        holder.view.subjectName.text=Class.CourseTitle
        holder.view.roomName.text=Class.Room
        holder.view.teacherName.text=Class.Teacher
        holder.view.startTime.text=Class.Time


    }

    class MovieViewHolder(val view: View):RecyclerView.ViewHolder(view)

}