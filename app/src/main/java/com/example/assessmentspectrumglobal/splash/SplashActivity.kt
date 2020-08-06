package com.example.assessmentspectrumglobal.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assessmentspectrumglobal.R
import com.example.assessmentspectrumglobal.dashboard.DashboardActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        GlobalScope.launch {
            delay(2000)
            startActivity(Intent(this@SplashActivity, DashboardActivity::class.java))
            finish()
        }

    }
}