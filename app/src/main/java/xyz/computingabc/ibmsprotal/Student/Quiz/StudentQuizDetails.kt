package xyz.computingabc.ibmsprotal.Student.Quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_student_quiz_details.*
import kotlinx.android.synthetic.main.activity_student_quiz_details.subjectName
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.computingabc.ibmsprotal.Preferences.LoginPreferences
import xyz.computingabc.ibmsprotal.R
import xyz.computingabc.ibmsprotal.Student.Quiz.Api.RetrofitClient
import xyz.computingabc.ibmsprotal.Student.Quiz.Data.QuizMarksResponse
import xyz.computingabc.ibmsprotal.Student.Quiz.Model.QuizMarksAdopter
import xyz.computingabc.ibmsprotal.Student.StudentLogin.StudentLogin
import xyz.computingabc.ibmsprotal.Student.StudentProfile

class StudentQuizDetails : AppCompatActivity() {
    var Subject: String? = null
    var Subject_code:String? = null
    var student_histry_id:String? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_quiz_details)
        toolbarSupport()
        Subject=intent.getStringExtra("Subject")
        Subject_code=intent.getStringExtra("Subject_code")
        val sharedPreferences: LoginPreferences = LoginPreferences(this)
        student_histry_id =sharedPreferences.getValueString("history_id")
        subjectName.text=Subject
        btnBack.setOnClickListener {
            startActivity(Intent(applicationContext, StudentQuizHome::class.java))
            finish()
        }
fatchStudentQuizMarks()

    }
    private fun fatchStudentQuizMarks(){
        //Toast.makeText(applicationContext,Subject_code+" / "+ student_histry_id, Toast.LENGTH_LONG).show()

        RetrofitClient.instance.fetchQuizMarks(Subject_code!!,student_histry_id!!)
            .enqueue(object : Callback<QuizMarksResponse> {
                override fun onResponse(call: Call<QuizMarksResponse>?, response: Response<QuizMarksResponse>?) {
                    val myresponse = response!!.body().marks

                    Toast.makeText(applicationContext,"this message", Toast.LENGTH_LONG).show()
                    QuizList.layoutManager =  LinearLayoutManager(applicationContext)
                    QuizList.adapter= QuizMarksAdopter(myresponse)
                }

                override fun onFailure(call: Call<QuizMarksResponse>?, t: Throwable?) {
                    Toast.makeText(applicationContext,"error message"+t, Toast.LENGTH_LONG).show()
                }

            })
    }

    private fun toolbarSupport(){

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.setDisplayShowTitleEnabled(false)
        toolbar_title.text="Quiz"
        toolbar_icon.setImageResource(R.drawable.ic_receipt_24px)
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
        startActivity(Intent(applicationContext, StudentQuizHome::class.java))
        finish()
    }
}
