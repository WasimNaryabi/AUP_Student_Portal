package xyz.computingabc.ibmsprotal.Student.Results.Model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.student_midtrem_marks_layout.view.*
import kotlinx.android.synthetic.main.student_subjects_list_layout.view.subjectName
import xyz.computingabc.ibmsprotal.Preferences.LoginPreferences
import xyz.computingabc.ibmsprotal.R
import xyz.computingabc.ibmsprotal.Student.Results.Data.MidMarks

class StudentMidMarksAdopter (val Marks:List<MidMarks>) : RecyclerView.Adapter<StudentMidMarksAdopter.MovieViewHolder>() {

    private var context: Context? = null
    // This funtion is used for attach layout to adopter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        context=parent.context
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.student_midtrem_marks_layout,parent,false)
        )

    }

    // This funtion is used for finding size of list
    override fun getItemCount()=Marks.size

    // This funtion is used for binding the data
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
       val  sharedPreferences: LoginPreferences = LoginPreferences(context!!)
        val Marks= Marks[position]
        holder.view.subjectName.text = Marks.Subject
        holder.view.details.text=Marks.QuizMarks+"|"+Marks.AssaignmentMarks+"|"+Marks.MidMarks
        holder.view.marks.text = Marks.Marks
        sharedPreferences.saveData("total",Marks.Total)
    }

    class MovieViewHolder(val view: View):RecyclerView.ViewHolder(view)

}