package com.vk.salestestapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.vk.salestestapp.ViewModel.MainActivityViewModel
import com.vk.salestestapp.databinding.ActivityMainBinding


class MainActivity : ComponentActivity() {

    private lateinit var edtEmail : EditText
    private lateinit var edtSubject : EditText
    private lateinit var edtMessage : EditText
    private lateinit var btnSend : Button
    var mainActivityViewModel: MainActivityViewModel?=null
    var count:Long=0

    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        edtEmail = findViewById(R.id.emailAddress)
        edtSubject = findViewById(R.id.subject)
        edtMessage = findViewById(R.id.message)
        btnSend = findViewById(R.id.sendBtn)

        mainActivityViewModel= ViewModelProvider(this)[MainActivityViewModel::class.java]
        mainActivityViewModel!!.getMovieList.observe(this){ movieModels ->

            Log.e("MainActivity","MovieList-->"+ movieModels[1].description)
            Log.e("MainActivity","SizeofList-->"+movieModels.size)

            for (i in 0 until movieModels.size) {

                count+= movieModels[i].available?.toInt() ?:
                Log.d("COUNT-->","COUNT-->$count")
                Log.e("COUNT-->","COUNT-->"+count)
            }
            Log.e("FINALCOUNT-->","FINALCOUNT-->"+count)
            (edtMessage as TextView).text = "Total available shirts -"+ count.toString()
            (edtSubject as TextView).text = "Today Sales update"
            (edtEmail as TextView).text = "bossman@bosscompany.com"

            btnSend.setOnClickListener {
                val strEmail = edtEmail.text.toString()
                val  strSubject = edtSubject.text.toString()
               val strMessage = edtMessage.text.toString()
                Log.e("COUNTY-->","COUNTY-->"+strSubject)
                Log.e("COUNTY-->","COUNTY-->"+strMessage)
                val addresses=strEmail.split(",".toRegex()).toTypedArray()
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL,addresses)
                    putExtra(Intent.EXTRA_SUBJECT,strSubject)
                    putExtra(Intent.EXTRA_TEXT,strMessage)
                }
                if (intent.resolveActivity(packageManager)!=null){
                    startActivity(intent)
                }else{
                    startActivity(intent)
                    //Toast.makeText(this@MainActivity,"Required app is not installed",Toast.LENGTH_LONG).show()
                }


                /*val mIntent = Intent(Intent.ACTION_SEND)
                mIntent.data = Uri.parse("mailto:")
                mIntent.type = "text/plain"
                mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(strEmail))
                mIntent.putExtra(Intent.EXTRA_SUBJECT, arrayOf(strSubject))
                mIntent.putExtra(Intent.EXTRA_TEXT, arrayOf(strMessage))
                try {
                    startActivity(Intent.createChooser(mIntent,"Send Email"))

                }catch (e:Exception){
                    Toast.makeText(this,e.message,Toast.LENGTH_LONG).show()

                }*/

            }

        }

    }
}
