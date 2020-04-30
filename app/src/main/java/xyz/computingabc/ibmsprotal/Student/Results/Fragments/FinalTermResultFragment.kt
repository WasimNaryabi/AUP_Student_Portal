package xyz.computingabc.ibmsprotal.Student.Results.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_finaltrem_result.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.computingabc.ibmsprotal.Preferences.LoginPreferences
import xyz.computingabc.ibmsprotal.R
import xyz.computingabc.ibmsprotal.Student.Results.Api.RetrofitClient
import xyz.computingabc.ibmsprotal.Student.Results.Data.FinalMarksRespons
import xyz.computingabc.ibmsprotal.Student.Results.Data.MidMarks
import xyz.computingabc.ibmsprotal.Student.Results.Data.MidMarksRespons
import xyz.computingabc.ibmsprotal.Student.Results.Model.StudentFinalMarksAdopter
import xyz.computingabc.ibmsprotal.Student.Results.Model.StudentMidMarksAdopter

class FinalTermResultFragment: Fragment() {
    var Discipline_id: String? = null
    var Semester_id:String? = null
    var student_histry_id:String? =null
    var TotalMarks:String? = null
    var TotalGPA:String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_finaltrem_result,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val sharedPreferences: LoginPreferences = LoginPreferences(activity as Context)
        Discipline_id =sharedPreferences.getValueString("discipline_id")
        Semester_id =sharedPreferences.getValueString("semester_id")
        student_histry_id =sharedPreferences.getValueString("history_id")
        TotalMarks =sharedPreferences.getValueString("final_total")
        TotalGPA =sharedPreferences.getValueString("total_gpa")
        fatchStudentMidMarks()
        totalFinalMarks.text=TotalMarks
        totalGPA.text=TotalGPA

    }

    private fun fatchStudentMidMarks(){

        Toast.makeText(activity as Context,Discipline_id+" / "+ student_histry_id+" / "+Semester_id, Toast.LENGTH_LONG).show()

        RetrofitClient.instance.fetchFinalMarks(student_histry_id!!,Discipline_id!!,Semester_id!!)
            .enqueue(object : Callback<FinalMarksRespons> {
                override fun onFailure(call: Call<FinalMarksRespons>?, t: Throwable?) {
                    Toast.makeText(activity as Context,"Error -> "+t, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<FinalMarksRespons>?, response: Response<FinalMarksRespons>?) {
                    val myresponse = response!!.body().finalMarks

                    MarksList.layoutManager= LinearLayoutManager(activity as Context?)
                    MarksList.adapter= StudentFinalMarksAdopter(myresponse)
                }



            })
    }

}