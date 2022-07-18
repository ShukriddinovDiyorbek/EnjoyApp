package com.example.enjoyapp

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.enjoyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"
    private val SMS_BODY = "body"
    private val SMS_SENDER = "address"
    val requestCode = 1001


    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED){
            getSms()
            Log.d(TAG, "onCreate: wrer")
        }else{
            requestSContactPermissiopn()
            Log.d(TAG, "onCreate: redwcfwwwww")
        }
    }

    private fun requestSContactPermissiopn() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_SMS), requestCode)
            Log.d(TAG, "requestSContactPermissiopn: wfde")
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_SMS), requestCode)
            Log.d(TAG, "requestSContactPermissiopn: fewcre")
        }
    }

    private fun getSms() {
        val uri = Uri.parse("content://sms/inbox")
        val projection = arrayOf(SMS_SENDER, SMS_BODY)
        val cursor: Cursor = contentResolver.query(uri, projection, null, null, null)!!

        while (cursor.moveToNext()){
            for (i in 0 until cursor.columnCount){
                Log.d(TAG, "getSms: ${i} - ${cursor.getColumnName(i)} - ${cursor.getString(i)}")
            }
        }
    }
}