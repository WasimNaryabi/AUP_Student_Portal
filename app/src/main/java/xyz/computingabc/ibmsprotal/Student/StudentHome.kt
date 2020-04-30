package xyz.computingabc.ibmsprotal.Student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_student_home.*
import kotlinx.android.synthetic.main.exit_dialog_layout.view.*
import kotlinx.android.synthetic.main.toolbar.*
import xyz.computingabc.ibmsprotal.Preferences.LoginPreferences
import xyz.computingabc.ibmsprotal.R
import xyz.computingabc.ibmsprotal.Student.Assignments.StudentAssignmentHome
import xyz.computingabc.ibmsprotal.Student.Attendance.StudentAttendanceHome
import xyz.computingabc.ibmsprotal.Student.Lectures.LecturesSubjects
import xyz.computingabc.ibmsprotal.Student.Quiz.StudentQuizHome
import xyz.computingabc.ibmsprotal.Student.Results.StudentResult
import xyz.computingabc.ibmsprotal.Student.StudentLogin.StudentLogin
import xyz.computingabc.ibmsprotal.Student.TimeTable.StudentTimetableHome

class StudentHome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_home)
        toolbarSupport()

        btnLectures.setOnClickListener {
            var intent = Intent(applicationContext,LecturesSubjects::class.java)
            startActivity(intent)
            finish()
        }
        btnAttendance.setOnClickListener {
            var intent = Intent(applicationContext,StudentAttendanceHome::class.java)
            startActivity(intent)
            finish()
        }
        btnTimeTable.setOnClickListener {
            var intent = Intent(applicationContext,StudentTimetableHome::class.java)
            startActivity(intent)
            finish()
        }

        btnQuiz.setOnClickListener {
            var intent = Intent(applicationContext,StudentQuizHome::class.java)
            startActivity(intent)
            finish()
        }
        btnMidExam.setOnClickListener {
            var intent = Intent(applicationContext,StudentResult::class.java)
            startActivity(intent)
            finish()
        }
        btnAssignment.setOnClickListener {
            val sharedPreferences: LoginPreferences = LoginPreferences(this)
           // sharedPreferences.clearSharedPreference()
            var intent = Intent(applicationContext, StudentAssignmentHome::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun toolbarSupport(){

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.setDisplayShowTitleEnabled(false)
        toolbar_title.text="Student"
        toolbar_icon.setImageResource(R.drawable.ic_home)
        btnBack.visibility = View.GONE


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
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.exit_dialog_layout, null)
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(this,R.style.CustomAlertDialog)
            .setView(mDialogView)
        mDialogView.dialogMessage.text ="Are you sure to Exit"
        //show dialog
        val  mAlertDialog = mBuilder.show()
        mAlertDialog.setCancelable(false)
        //login button click of custom layout
        mDialogView.btnNO.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()

        }

        mDialogView.btnYes.setOnClickListener {
            finish()
        }
    }
}
