package com.kei.exoplayertrial

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kei.exoplayertrial.playList.PlayListActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        val intent = Intent(this, PlayListActivity::class.java)
        startActivity(intent)
    }
}
