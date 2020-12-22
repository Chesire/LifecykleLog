package com.chesire.lifecyklelogsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chesire.lifecyklelog.LogLifecykle

/**
 * [AppCompatActivity] to show how to use [LogLifecykle].
 */
@LogLifecykle(className = "MainActivity")
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.activityMainContainer, MainFragment.newInstance())
                .commit()
        }
    }
}
