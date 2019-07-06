package com.chesire.lifecyklelog.events

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.chesire.lifecyklelog.LifecycleEvent
import com.chesire.lifecyklelog.LifecykleLog

/**
 * Handles callbacks for all of the [Fragment] lifecycle events.
 */
@Suppress("TooManyFunctions")
internal val fragmentCallbacks = object : FragmentManager.FragmentLifecycleCallbacks() {
    override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
        LifecykleLog.logLifecycle(f, LifecycleEvent.ON_ATTACH)
        super.onFragmentAttached(fm, f, context)
    }

    override fun onFragmentCreated(
        fm: FragmentManager,
        f: Fragment,
        savedInstanceState: Bundle?
    ) {
        LifecykleLog.logLifecycle(f, LifecycleEvent.ON_CREATE)
        super.onFragmentCreated(fm, f, savedInstanceState)
    }

    override fun onFragmentViewCreated(
        fm: FragmentManager,
        f: Fragment,
        v: View,
        savedInstanceState: Bundle?
    ) {
        LifecykleLog.logLifecycle(f, LifecycleEvent.ON_CREATE_VIEW)
        super.onFragmentViewCreated(fm, f, v, savedInstanceState)
    }

    override fun onFragmentActivityCreated(
        fm: FragmentManager,
        f: Fragment,
        savedInstanceState: Bundle?
    ) {
        LifecykleLog.logLifecycle(f, LifecycleEvent.ON_ACTIVITY_CREATED)
        super.onFragmentActivityCreated(fm, f, savedInstanceState)
    }

    override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
        LifecykleLog.logLifecycle(f, LifecycleEvent.ON_START)
        super.onFragmentStarted(fm, f)
    }

    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
        LifecykleLog.logLifecycle(f, LifecycleEvent.ON_RESUME)
        super.onFragmentResumed(fm, f)
    }

    override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
        LifecykleLog.logLifecycle(f, LifecycleEvent.ON_PAUSE)
        super.onFragmentPaused(fm, f)
    }

    override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
        LifecykleLog.logLifecycle(f, LifecycleEvent.ON_STOP)
        super.onFragmentStopped(fm, f)
    }

    override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
        LifecykleLog.logLifecycle(f, LifecycleEvent.ON_DESTROY_VIEW)
        super.onFragmentViewDestroyed(fm, f)
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        LifecykleLog.logLifecycle(f, LifecycleEvent.ON_DESTROY)
        super.onFragmentDestroyed(fm, f)
    }

    override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
        LifecykleLog.logLifecycle(f, LifecycleEvent.ON_DETACH)
        super.onFragmentDetached(fm, f)
    }

    override fun onFragmentPreAttached(fm: FragmentManager, f: Fragment, context: Context) {
        LifecykleLog.logLifecycle(f, LifecycleEvent.ON_PRE_ATTACHED)
        super.onFragmentPreAttached(fm, f, context)
    }

    override fun onFragmentPreCreated(
        fm: FragmentManager,
        f: Fragment,
        savedInstanceState: Bundle?
    ) {
        LifecykleLog.logLifecycle(f, LifecycleEvent.ON_PRE_CREATED)
        super.onFragmentPreCreated(fm, f, savedInstanceState)
    }

    override fun onFragmentSaveInstanceState(
        fm: FragmentManager,
        f: Fragment,
        outState: Bundle
    ) {
        LifecykleLog.logLifecycle(f, LifecycleEvent.ON_SAVE_INSTANCE_STATE)
        super.onFragmentSaveInstanceState(fm, f, outState)
    }
}
