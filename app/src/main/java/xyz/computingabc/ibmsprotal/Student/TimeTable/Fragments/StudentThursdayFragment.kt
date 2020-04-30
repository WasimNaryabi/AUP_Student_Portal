package xyz.computingabc.ibmsprotal.Student.TimeTable.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_timetable.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.computingabc.ibmsprotal.Preferences.LoginPreferences
import xyz.computingabc.ibmsprotal.R
import xyz.computingabc.ibmsprotal.Student.TimeTable.Api.RetrofitClient
import xyz.computingabc.ibmsprotal.Student.TimeTable.Data.StudentTimetable
import xyz.computingabc.ibmsprotal.Student.TimeTable.Data.TimeTableResponse
import xyz.computingabc.ibmsprotal.Student.TimeTable.Model.StudentMondayTimeTableAdopter

class StudentThursdayFragment: Fragment() {

    var Discipline_id: String? = null
    var Semester_id:String? = null
    var Session_id:String? =null
    var Section_id:String? = null
    var Day_id:String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_timetable,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val sharedPreferences: LoginPreferences = LoginPreferences(activity as Context)
        Discipline_id =sharedPreferences.getValueString("discipline_id")
        Semester_id =sharedPreferences.getValueString("semester_id")
        Session_id =sharedPreferences.getValueString("session_id")
        Section_id =sharedPreferences.getValueString("section_id")
        Day_id ="4"

        fatchStudentTimeTable()

    }

    private fun fatchStudentTimeTable(){

        RetrofitClient.instance.fetchTimetable(Discipline_id!!,Session_id!!,Semester_id!!,Section_id!!,Day_id!!)
            .enqueue(object : Callback<TimeTableResponse> {
                override fun onFailure(call: Call<TimeTableResponse>?, t: Throwable?) {
                    Toast.makeText(activity as Context,"Error -> "+t, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<TimeTableResponse>?, response: Response<TimeTableResponse>?) {
                    val myresponse = response!!.body().classes

                    classesTimetable.layoutManager= LinearLayoutManager(activity as Context?)
                    classesTimetable.adapter= StudentMondayTimeTableAdopter(myresponse)
                }



            })
    }

}