package xyz.computingabc.ibmsprotal.Student.Assignments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_student_assignment_marks.*
import kotlinx.android.synthetic.main.activity_student_quiz_details.*
import kotlinx.android.synthetic.main.activity_student_quiz_details.subjectName
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.computingabc.ibmsprotal.Preferences.LoginPreferences
import xyz.computingabc.ibmsprotal.R
import xyz.computingabc.ibmsprotal.Student.Assignments.Api.RetrofitClient
import xyz.computingabc.ibmsprotal.Student.Assignments.Data.AssignmentMarksResponse
import xyz.computingabc.ibmsprotal.Student.Assignments.Model.AssignmentMarksAdopter
import xyz.computingabc.ibmsprotal.Student.Quiz.Model.QuizMarksAdopter
import xyz.computingabc.ibmsprotal.Student.StudentLogin.StudentLogin
import xyz.computingabc.ibmsprotal.Student.StudentProfile

class StudentAssignmentMarksDetails : AppCompatActivity() {
    var Subject: String? = null
    var Subject_code:String? = null
    var student_histry_id:String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_assignment_marks)
        toolbarSupport()
        Subject=intent.getStringExtra("Subject")
        Subject_code=intent.getStringExtra("Subject_code")
        val sharedPreferences: LoginPreferences = LoginPreferences(this)
        student_histry_id =sharedPreferences.getValueString("history_id")
        subjectName.text=Subject
        btnBack.setOnClickListener {
            startActivity(Intent(applicationContext, StudentAssignmentHome::class.java))
            finish()
        }

        fatchStudentQuizMarks()

    }
    private fun fatchStudentQuizMarks(){
        Toast.makeText(applicationContext,Subject_code+" / "+ student_histry_id, Toast.LENGTH_LONG).show()

        RetrofitClient.instance.fetchAssigmnetMarks(Subject_code!!,student_histry_id!!)
            .enqueue(object : Callback<AssignmentMarksResponse> {
                override fun onResponse(call: Call<AssignmentMarksResponse>?, response: Response<AssignmentMarksResponse>?) {
                    val myresponse = response!!.body().marks

                    Toast.makeText(applicationContext,"this message", Toast.LENGTH_LONG).show()
                    AssignmentsMarksList.layoutManager =  LinearLayoutManager(applicationContext)
                    AssignmentsMarksList.adapter= AssignmentMarksAdopter(myresponse)
                }

                override fun onFailure(call: Call<AssignmentMarksResponse>?, t: Throwable?) {
                    Toast.makeText(applicationContext,"error message"+t, Toast.LENGTH_LONG).show()
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
        startActivity(Intent(applicationContext, StudentAssignmentHome::class.java))
        finish()
    }
}
