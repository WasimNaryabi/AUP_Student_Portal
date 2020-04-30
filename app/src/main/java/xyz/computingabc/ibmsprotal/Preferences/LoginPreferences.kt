package xyz.computingabc.ibmsprotal.Preferences

import android.content.Context
import android.content.SharedPreferences

class LoginPreferences(val context: Context) {

    private val PREFS_NAME = "User_Details"
    val sharedPref = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)

    fun saveData(KEY_NAME:String,text:String){
        val editor = sharedPref.edit()
        editor.putString(KEY_NAME,text)
        editor.commit()
    }

    fun saveData(KEY_NAME:String,value:Int){
        val editor = sharedPref.edit()
        editor.putInt(KEY_NAME,value)
        editor.commit()
    }

    fun getValueString(KEY_NAME: String):String?{
        return sharedPref.getString(KEY_NAME,null)
    }

    fun getValueInt(KEY_NAME: String):Int{
        return sharedPref.getInt(KEY_NAME,0)
    }

    fun clearSharedPreference(){
        val editor = sharedPref.edit()
        editor.clear()
        editor.commit()
    }

    fun removeValue(KEY_NAME: String){
        val editor = sharedPref.edit()
        editor.remove(KEY_NAME)
        editor.commit()
    }

}