package xyz.computingabc.ibmsprotal.Student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_student_profile.*
import kotlinx.android.synthetic.main.toolbar.*
import xyz.computingabc.ibmsprotal.Preferences.LoginPreferences
import xyz.computingabc.ibmsprotal.R
import xyz.computingabc.ibmsprotal.Student.StudentLogin.StudentLogin

class StudentProfile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_profile)
        toolbarSupport()

        val sharedPreferences: LoginPreferences = LoginPreferences(this)
       // val userEmail =sharedPreferences.getValueString("Email")
      //  val userPassword =sharedPreferences.getValueString("Password")
        val userName =sharedPreferences.getValueString("Name")
        val userFatherName = sharedPreferences.getValueString("FatherName")
        val userGender =sharedPreferences.getValueString("Gender")
        val userCnic = sharedPreferences.getValueString("CNIC")
        val userDob = sharedPreferences.getValueString("DOB")
        val userPhone =sharedPreferences.getValueString("Phone")
        val userAddress =sharedPreferences.getValueString("Address")
        val userDiscipline = sharedPreferences.getValueString("Discipline")
        val userSession =sharedPreferences.getValueString("Session")
        val userSemester =sharedPreferences.getValueString("Semester")
        val userSection=sharedPreferences.getValueString("Section")
        val userClassNo=sharedPreferences.getValueString("classNo")

        val basicInfo:String?

        if(userGender.equals("Male") || userGender.equals("male")) {
            basicInfo = "$userName S/O $userFatherName"
        }else {
            basicInfo = "$userName D/O $userFatherName"
        }

        basicStdName.text=basicInfo
        stdCNIC.text =userCnic
        stdDob.text = userDob
        stdPhone.text=userPhone
       // stdEmail.text =userEmail
        stdAddress.text=userAddress
        stdDiscipline.text =userDiscipline
        stdSession.text=userSession
        stdSection.text=userSection
        stdSemester.text=userSemester
        stdClassno.text = userClassNo



        btnBack.setOnClickListener {
            startActivity(Intent(applicationContext,StudentHome::class.java))
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
