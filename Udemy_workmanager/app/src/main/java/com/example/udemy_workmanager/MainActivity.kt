package com.example.udemy_workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button2.setOnClickListener(View.OnClickListener {
            setOntimeWorkRequets()
        })
    }

    private fun setOntimeWorkRequets(){
        val workManager=WorkManager.getInstance(applicationContext)
        val constraints=Constraints.Builder()    //setting constratints
                .setRequiresCharging(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        val uploadRequest=OneTimeWorkRequest.Builder(UploadWorker::class.java)
                .setConstraints(constraints)
                .build()
        workManager.enqueue(uploadRequest)

        //getting work state/status
        workManager.getWorkInfoByIdLiveData(uploadRequest.id)
                .observe(this, androidx.lifecycle.Observer {
                    textView.text = it.state.name
                })
    }
}