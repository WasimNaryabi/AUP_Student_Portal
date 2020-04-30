package xyz.computingabc.ibmsprotal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.toolbar.*
import xyz.computingabc.ibmsprotal.Student.StudentLogin.StudentLogin

class AboutApp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_app)
        toolbarSupport()
        btnBack.setOnClickListener {
            startActivity(Intent(applicationContext,MainActivity::class.java))
            finish()
        }
    }

    private fun toolbarSupport(){
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.setDisplayShowTitleEnabled(false)
        toolbar_title.text="About App"
        toolbar_icon.setImageResource(R.drawable.ic_phone_android)
        //btnBack.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_teacher, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.aboutapp) {
            startActivity(Intent(applicationContext,AboutApp::class.java))
            finish()
            return true
        }
        if (id == R.id.exit) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        startActivity(Intent(applicationContext,StudentLogin::class.java))
        finish()
    }
}
