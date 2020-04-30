package xyz.computingabc.ibmsprotal.Student.Assignments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_student_assignment_home.SubjectList
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.computingabc.ibmsprotal.Preferences.LoginPreferences
import xyz.computingabc.ibmsprotal.R
import xyz.computingabc.ibmsprotal.Student.Assignments.Model.AssignmentMarksSubjectsAdopter
import xyz.computingabc.ibmsprotal.Student.Lectures.Api.RetrofitClinet
import xyz.computingabc.ibmsprotal.Student.Lectures.Data.SubjectResponse
import xyz.computingabc.ibmsprotal.Student.StudentHome
import xyz.computingabc.ibmsprotal.Student.StudentLogin.StudentLogin
import xyz.computingabc.ibmsprotal.Student.StudentProfile

class StudentAssignmentHome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_assignment_home)
        toolbarSupport()
        btnBack.setOnClickListener {
            startActivity(Intent(applicationContext, StudentHome::class.java))
            finish()
        }
        fatchClasses()
    }

    private fun fatchClasses(){
        val sharedPreferences: LoginPreferences = LoginPreferences(applicationContext)
        val disciplin_id =sharedPreferences.getValueString("discipline_id")
        val semseter_id =sharedPreferences.getValueString("semester_id")

        Toast.makeText(applicationContext,"id ="+disciplin_id+" / "+semseter_id, Toast.LENGTH_SHORT).show()
       RetrofitClinet.instance.fatchSubjects(disciplin_id!!,semseter_id!!)
            .enqueue(object : Callback<SubjectResponse> {
                override fun onFailure(call: Call<SubjectResponse>?, t: Throwable?) {
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<SubjectResponse>?, response: Response<SubjectResponse>?) {
                    val myresponse = response!!.body().subjects
                    SubjectList.layoutManager= LinearLayoutManager(applicationContext)
                    SubjectList.adapter= AssignmentMarksSubjectsAdopter(myresponse)
                }

            })
    }

    private fun toolbarSupport(){

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.setDisplayShowTitleEnabled(false)
        toolbar_title.text="Assignment"
        toolbar_icon.setImageResource(R.drawable.ic_description_24px)
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
        startActivity(Intent(applicationContext, StudentHome::class.java))
        finish()
    }
}
