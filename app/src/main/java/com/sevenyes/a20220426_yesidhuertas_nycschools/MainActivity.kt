package com.sevenyes.a20220426_yesidhuertas_nycschools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sevenyes.a20220426_yesidhuertas_nycschools.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}