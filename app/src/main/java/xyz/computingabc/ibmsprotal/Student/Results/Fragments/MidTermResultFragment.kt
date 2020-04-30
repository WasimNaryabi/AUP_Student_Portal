package xyz.computingabc.ibmsprotal.Student.Results.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_student_quiz_details.*
import kotlinx.android.synthetic.main.fragment_midtrem_result.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.computingabc.ibmsprotal.Preferences.LoginPreferences
import xyz.computingabc.ibmsprotal.R
import xyz.computingabc.ibmsprotal.Student.Results.Api.RetrofitClient
import xyz.computingabc.ibmsprotal.Student.Quiz.Data.QuizMarksResponse
import xyz.computingabc.ibmsprotal.Student.Quiz.Model.QuizMarksAdopter
import xyz.computingabc.ibmsprotal.Student.Results.Data.MidMarks
import xyz.computingabc.ibmsprotal.Student.Results.Data.MidMarksRespons
import xyz.computingabc.ibmsprotal.Student.Results.Model.StudentMidMarksAdopter

class MidTermResultFragment: Fragment() {
    var Discipline_id: String? = null
    var Semester_id:String? = null
    var student_histry_id:String? =null
    var TotalMarks:String? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_midtrem_result,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val sharedPreferences: LoginPreferences = LoginPreferences(activity as Context)
        Discipline_id =sharedPreferences.getValueString("discipline_id")
        Semester_id =sharedPreferences.getValueString("semester_id")
        student_histry_id =sharedPreferences.getValueString("history_id")

        TotalMarks =sharedPreferences.getValueString("total")
        Toast.makeText(activity as Context," Total Marks is "+ TotalMarks, Toast.LENGTH_LONG).show()

        fatchStudentMidMarks()
        totalMarks.text=TotalMarks

    }

    private fun fatchStudentMidMarks(){

        Toast.makeText(activity as Context,Discipline_id+" / "+ student_histry_id+" / "+Semester_id, Toast.LENGTH_LONG).show()

        RetrofitClient.instance.fetchMidMarks(student_histry_id!!,Discipline_id!!,Semester_id!!)
            .enqueue(object : Callback<MidMarksRespons> {
                override fun onFailure(call: Call<MidMarksRespons>?, t: Throwable?) {
                    Toast.makeText(activity as Context,"Error -> "+t, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<MidMarksRespons>?, response: Response<MidMarksRespons>?) {
                    val myresponse = response!!.body().midmarks

                    MarksList.layoutManager= LinearLayoutManager(activity as Context?)
                    MarksList.adapter= StudentMidMarksAdopter(myresponse)
                }



            })
    }

}