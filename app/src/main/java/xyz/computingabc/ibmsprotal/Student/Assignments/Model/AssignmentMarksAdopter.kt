package xyz.computingabc.ibmsprotal.Student.Assignments.Model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.quiz_marks_layout.view.*
import xyz.computingabc.ibmsprotal.R
import xyz.computingabc.ibmsprotal.Student.Assignments.Data.AssignmentMarks

class AssignmentMarksAdopter (val Marks:List<AssignmentMarks>) : RecyclerView.Adapter<AssignmentMarksAdopter.MovieViewHolder>() {

    // This funtion is used for attach layout to adopter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.quiz_marks_layout,parent,false)
        )
    }

    // This funtion is used for finding size of list
    override fun getItemCount()=Marks.size

    // This funtion is used for binding the data
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val Marks= Marks[position]
        holder.view.quizNo.text = "Assignment No "+Marks.AssNo
        holder.view.quizDescription.text = Marks.AssTopic
        //holder.view.date.text = Marks.Date
        holder.view.marks.text = Marks.Marks

    }

    class MovieViewHolder(val view: View):RecyclerView.ViewHolder(view)

}