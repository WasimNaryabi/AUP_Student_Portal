package xyz.computingabc.ibmsprotal.Student.Lectures.Model

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.student_subjects_list_layout.view.*
import xyz.computingabc.ibmsprotal.R
import xyz.computingabc.ibmsprotal.Student.Lectures.Data.Subjects
import xyz.computingabc.ibmsprotal.Student.Lectures.LecturesSubjects
import xyz.computingabc.ibmsprotal.Student.Lectures.StudentLecturesList

class StudentSubjectsAdopter (val Subjects:List<Subjects>) : RecyclerView.Adapter<StudentSubjectsAdopter.MovieViewHolder>() {

    // This funtion is used for attach layout to adopter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.student_subjects_list_layout,parent,false)
        )
    }

    // This funtion is used for finding size of list
    override fun getItemCount()=Subjects.size

    // This funtion is used for binding the data
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val Subject= Subjects[position]
        val context = holder.view.context
        val activity=context as LecturesSubjects
        holder.view.subjectName.text = Subject.course_name
        holder.itemView.setOnClickListener {

            var intent=Intent(context,StudentLecturesList::class.java)
            intent.putExtra("Subject",Subject.course_name)
            intent.putExtra("Subject_code",Subject.course_code)
            context.startActivity(intent)
            activity.finish()

        }


    }

    class MovieViewHolder(val view: View):RecyclerView.ViewHolder(view)

}