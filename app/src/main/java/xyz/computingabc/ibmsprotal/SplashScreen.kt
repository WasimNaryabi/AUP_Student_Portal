package xyz.computingabc.ibmsprotal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import xyz.computingabc.ibmsprotal.Preferences.LoginPreferences
import xyz.computingabc.ibmsprotal.Student.StudentHome
import xyz.computingabc.ibmsprotal.Student.StudentLogin.StudentLogin


class SplashScreen : AppCompatActivity() {

    private val splashTime:Long = 2000;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val sharedPreferences: LoginPreferences = LoginPreferences(this)
        val userType =sharedPreferences.getValueString("UserType")
        Toast.makeText(this,"Login Type :"+userType,Toast.LENGTH_LONG).show()
        val mRunnable= Runnable {
            if(!isFinishing){
                if(userType.equals("Student")){
                    startActivity(Intent(applicationContext, StudentHome::class.java))
                    finish()
                }else{
                    startActivity(Intent(applicationContext, StudentLogin::class.java))
                    finish()
                }
            }
        }
        Handler().postDelayed(mRunnable,splashTime)
    }
}
