package xyz.computingabc.ibmsprotal.Student.StudentLogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_student_login.btnLogin
import kotlinx.android.synthetic.main.activity_student_login.editTextEmail
import kotlinx.android.synthetic.main.activity_student_login.editTextPassword
import kotlinx.android.synthetic.main.exit_dialog_layout.view.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.computingabc.ibmsprotal.AboutApp
import xyz.computingabc.ibmsprotal.MainActivity
import xyz.computingabc.ibmsprotal.R
import xyz.computingabc.ibmsprotal.Student.StudentHome
import xyz.computingabc.ibmsprotal.Student.StudentLogin.Api.RetrofitClient
import xyz.computingabc.ibmsprotal.Student.StudentLogin.Data.LoginRespons
import xyz.computingabc.ibmsprotal.Student.StudentLogin.Storage.SharedPrefManager


class StudentLogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_login)

        toolbarSupport()
        btnLogin.setOnClickListener {
            var studentEmail = editTextEmail.text.toString()
            var studentPassword = editTextPassword.text.toString()

            if(studentEmail.isEmpty()){
                editTextEmail.error = "Email required"
                editTextEmail.requestFocus()
                return@setOnClickListener
            }

            if(studentPassword.isEmpty()){
                editTextPassword.error = "Password required"
                editTextPassword.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instance.userLogin(studentEmail,studentPassword)
                .enqueue(object : Callback<LoginRespons> {
                    override fun onFailure(call: Call<LoginRespons>?, t: Throwable?) {
                        Toast.makeText(applicationContext, t!!.message+" This message", Toast.LENGTH_LONG).show()
                    }
                    override fun onResponse(call: Call<LoginRespons>?, response: Response<LoginRespons>?) {
                        if(!response!!.body()?.error!!){
                            SharedPrefManager.getInstance(applicationContext).saveUser(response.body()?.student!!)
                            val intent = Intent(applicationContext, StudentHome::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
                        }
                    }
                })
        }


    }

    private fun toolbarSupport(){
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.setDisplayShowTitleEnabled(false)
        toolbar_title.text="Login"
        toolbar_icon.setImageResource(R.drawable.ic_person_black_24dp)
        btnBack.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.getItemId()

        if (id == R.id.aboutapp) {
            startActivity(Intent(applicationContext, AboutApp::class.java))
            finish()
            return true
        }
        if (id == R.id.exit) {
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
