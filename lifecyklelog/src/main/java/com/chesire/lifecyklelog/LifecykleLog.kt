package com.chesire.lifecyklelog

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

object LifecykleLog {
    fun initialize(app: Application) = setupActivity(app)

    private fun setupActivity(app: Application) =
        app.registerActivityLifecycleCallbacks(activityCallbacks)

    private fun setupFragment(activity: FragmentActivity) =
        activity.supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentCallbacks, true)

    private val activityCallbacks = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityPaused(activity: Activity) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onActivityResumed(activity: Activity) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onActivityStarted(activity: Activity) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onActivityDestroyed(activity: Activity) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onActivityStopped(activity: Activity) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            if (activity is FragmentActivity) {
                setupFragment(activity)
            }
        }
    }

    private val fragmentCallbacks = object : FragmentManager.FragmentLifecycleCallbacks() {

    }
}
