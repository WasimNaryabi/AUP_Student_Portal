package xyz.computingabc.ibmsprotal.Student.Attendance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_student_lectures_list.subjectName
import kotlinx.android.synthetic.main.activity_student_subject_attendance_list.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.computingabc.ibmsprotal.Preferences.LoginPreferences
import xyz.computingabc.ibmsprotal.R
import xyz.computingabc.ibmsprotal.Student.Attendance.Api.RetrofitClient
import xyz.computingabc.ibmsprotal.Student.Attendance.Data.AttendanceResponse
import xyz.computingabc.ibmsprotal.Student.Attendance.Model.StudentAttendanceAdopter
import xyz.computingabc.ibmsprotal.Student.StudentLogin.StudentLogin
import xyz.computingabc.ibmsprotal.Student.StudentProfile

class StudentSubjectAttendanceList : AppCompatActivity() {

    var Subject:String? = null
    var Subject_code:String? = null
    var student_histry_id:String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_subject_attendance_list)
        toolbarSupport()
        Subject=intent.getStringExtra("Subject")
        Subject_code=intent.getStringExtra("Subject_code")
        val sharedPreferences: LoginPreferences = LoginPreferences(this)
        student_histry_id =sharedPreferences.getValueString("history_id")
        subjectName.text=Subject

        fatchStudentAttendance()
        btnBack.setOnClickListener {
            startActivity(Intent(applicationContext, StudentAttendanceHome::class.java))
            finish()
        }

    }

    private fun fatchStudentAttendance(){
Toast.makeText(applicationContext,Subject_code+" / "+ student_histry_id,Toast.LENGTH_LONG).show()
        RetrofitClient.instance.fetchAttendance(Subject_code!!,student_histry_id!!)
            .enqueue(object : Callback<AttendanceResponse> {
                override fun onResponse(call: Call<AttendanceResponse>?, response: Response<AttendanceResponse>?) {
                    val myresponse = response!!.body().attendance
                    attendanceList.layoutManager=LinearLayoutManager(applicationContext)
                    attendanceList.adapter=StudentAttendanceAdopter(myresponse)
                }

                override fun onFailure(call: Call<AttendanceResponse>?, t: Throwable?) {
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            })
    }

    private fun toolbarSupport(){

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.setDisplayShowTitleEnabled(false)
        toolbar_title.text="Attendance"
        toolbar_icon.setImageResource(R.drawable.ic_assignment_turned_in_24px)
        //btnBack.visibility = View.GONE


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_teacher,menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.profile) {
            startActivity(Intent(applicationContext, StudentProfile::class.java))
            finish()
            return true
        }

        if (id == R.id.logout) {
            val sharedPreferences: LoginPreferences = LoginPreferences(this)
            sharedPreferences.clearSharedPreference()
            startActivity(Intent(applicationContext, StudentLogin::class.java))
            finish()
            return true
        }


        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        startActivity(Intent(applicationContext, StudentAttendanceHome::class.java))
        finish()
    }
}
