package xyz.computingabc.ibmsprotal.Student.StudentLogin.Storage

import android.content.Context
import xyz.computingabc.ibmsprotal.Preferences.LoginPreferences
import xyz.computingabc.ibmsprotal.Student.StudentLogin.Data.Student

class SharedPrefManager private constructor(private val mCtx: Context) {

    val isLoggedIn: Boolean
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getInt("faculty_id", -1) != -1
        }

    /*val teacher: Teacher
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return Teacher(
                sharedPreferences.getInt("faculty_id", -1),
                sharedPreferences.getString("user_name", null),
                sharedPreferences.getString("first_name", null),
                sharedPreferences.getString("last_name", null),
                sharedPreferences.getString("father_name", null),
                sharedPreferences.getString("gender", null),
                sharedPreferences.getString("user_type", null),
                sharedPreferences.getString("cnic", null),
                sharedPreferences.getString("mobno", null),
                sharedPreferences.getString("add", null)
            )
        }*/


    fun saveUser(user: Student) {

        val sharedPreferences: LoginPreferences = LoginPreferences(mCtx)
        sharedPreferences.saveData("student_id",user.student_id)
        //sharedPreferences.saveData("Email",user.user_name)
       //sharedPreferences.saveData("Password",teacherPassword)
        sharedPreferences.saveData("UserType",user.user_type)
        sharedPreferences.saveData("Name",user.first_name+" "+user.last_name)
        sharedPreferences.saveData("FatherName",user.father_name)
        sharedPreferences.saveData("Gender",user.gender)
        sharedPreferences.saveData("CNIC",user.cnic)
        sharedPreferences.saveData("DOB",user.dob)
        sharedPreferences.saveData("Phone",user.mobno)
        sharedPreferences.saveData("Address",user.add)
        sharedPreferences.saveData("classNo",user.classno)
        sharedPreferences.saveData("uni_reg_number",user.uni_reg_number)
        sharedPreferences.saveData("history_id",user.history_id)
        sharedPreferences.saveData("semester_id",user.semester_id)
        sharedPreferences.saveData("session_id",user.session_id)
        sharedPreferences.saveData("discipline_id",user.discipline_id)
        sharedPreferences.saveData("section_id",user.section_id)
        sharedPreferences.saveData("Semester",user.semester_name)
        sharedPreferences.saveData("Session",user.session_name)
        sharedPreferences.saveData("Discipline",user.discipline_name)
        sharedPreferences.saveData("Section",user.section_name)


    }

    fun clear() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private val SHARED_PREF_NAME = "my_shared_preff"
        private var mInstance: SharedPrefManager? = null
        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }

}