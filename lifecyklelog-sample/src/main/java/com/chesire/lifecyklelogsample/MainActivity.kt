package com.chesire.lifecyklelogsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chesire.lifecyklelog.LogLifecykle

/**
 * [AppCompatActivity] to show how to use [LogLifecykle].
 */
@LogLifecykle("MainActivity")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.activityMainContainer, MainFragment.newInstance())
                .commit()
        }
    }
}
