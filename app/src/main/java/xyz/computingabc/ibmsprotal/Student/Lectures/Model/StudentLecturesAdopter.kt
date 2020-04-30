package xyz.computingabc.ibmsprotal.Student.Lectures.Model

import android.Manifest
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.student_lectures_list_layout.view.*
import xyz.computingabc.ibmsprotal.R
import xyz.computingabc.ibmsprotal.Student.Lectures.StudentLecturesList
import xyz.computingabc.ibmsprotal.Student.Lectures.Data.Lectures
import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.core.content.ContextCompat.getSystemService
import android.system.Os.mkdir
import android.util.Log
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat.checkSelfPermission
import java.io.File
import java.nio.file.Files.exists
import kotlin.math.log


class StudentLecturesAdopter (val Lecture:List<Lectures>) : RecyclerView.Adapter<StudentLecturesAdopter.MovieViewHolder>() {

    private val STORAGE_CODE: Int = 1000

    // This funtion is used for attach layout to adopter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.student_lectures_list_layout,parent,false)
        )
    }

    // This funtion is used for finding size of list0
    override fun getItemCount()=Lecture.size

    // This funtion is used for binding the data
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val Lecture= Lecture[position]
        var Numeber=position+1
        val context = holder.view.context
        val activity=context as StudentLecturesList
        holder.view.lectureNo.text="Lecture $Numeber"
        holder.view.lectureTopic.text=Lecture.Topic
        holder.view.Date.text=Lecture.Date
        var path:String=Lecture.Path
        holder.view.downloadFile.setOnClickListener {

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(checkSelfPermission(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                    Toast.makeText(activity,"A",Toast.LENGTH_SHORT).show()
                 requestPermissions(activity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),STORAGE_CODE)
                }
                else{
                    Toast.makeText(activity,"B",Toast.LENGTH_SHORT).show()
                    val downloadmanager = activity.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?
                    val uri = Uri.parse("http://192.168.1.13/UniPortal/lectures/$path")

                    val request = DownloadManager.Request(uri)
                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                    request.setTitle("My File")
                    request.setDescription("Downloading")
                    request.allowScanningByMediaScanner()
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    request.setVisibleInDownloadsUi(true)
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, path)


                    downloadmanager!!.enqueue(request)

                    Toast.makeText(context,"downloading $path",Toast.LENGTH_SHORT).show()
                }
            }else{
                val downloadmanager = activity.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?
                val uri = Uri.parse("http://192.168.1.13/UniPortal/lectures/$path")

                val request = DownloadManager.Request(uri)
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                request.setTitle("My File")
                request.setDescription("Downloading")
                request.allowScanningByMediaScanner()
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                request.setVisibleInDownloadsUi(true)
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, path)


                downloadmanager!!.enqueue(request)

                Toast.makeText(context,"downloading $path",Toast.LENGTH_SHORT).show()
            }


        }



    }

    class MovieViewHolder(val view: View):RecyclerView.ViewHolder(view)

}