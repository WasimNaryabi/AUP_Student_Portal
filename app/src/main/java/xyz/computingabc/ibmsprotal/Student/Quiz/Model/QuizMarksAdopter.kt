package xyz.computingabc.ibmsprotal.Student.Quiz.Model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.quiz_marks_layout.view.*
import xyz.computingabc.ibmsprotal.R
import xyz.computingabc.ibmsprotal.Student.Quiz.Data.QuizMarks

class QuizMarksAdopter (val Marks:List<QuizMarks>) : RecyclerView.Adapter<QuizMarksAdopter.MovieViewHolder>() {

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
        holder.view.quizNo.text = "Quiz No "+Marks.QuizNo
        holder.view.quizDescription.text = Marks.QuizTopic
        holder.view.marks.text = Marks.Marks

    }

    class MovieViewHolder(val view: View):RecyclerView.ViewHolder(view)

}