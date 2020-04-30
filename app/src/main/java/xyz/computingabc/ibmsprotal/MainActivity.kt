package xyz.computingabc.ibmsprotal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.exit_dialog_layout.view.*
import kotlinx.android.synthetic.main.toolbar.*
import xyz.computingabc.ibmsprotal.Student.StudentLogin.StudentLogin


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarSupport()



        btnStudent.setOnClickListener {
            startActivity(Intent(applicationContext, StudentLogin::class.java))
            finish()
        }
    }

    private fun toolbarSupport(){
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.setDisplayShowTitleEnabled(false)
        toolbar_title.text="Home"
        toolbar_icon.setImageResource(R.drawable.ic_home)
        btnBack.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatica lly handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.aboutapp) {
            startActivity(Intent(applicationContext,AboutApp::class.java))
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
