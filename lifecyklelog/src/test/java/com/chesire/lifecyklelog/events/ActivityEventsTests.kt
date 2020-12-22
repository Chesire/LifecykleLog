package com.chesire.lifecyklelog.events

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.chesire.lifecyklelog.LifecycleEvent
import com.chesire.lifecyklelog.LifecykleLog
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkObject
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test

class ActivityEventsTests {
    @Before
    fun setup() = mockkObject(LifecykleLog)

    @After
    fun teardown() = unmockkObject(LifecykleLog)

    @Test
    fun `onActivityCreated sets up fragment lifecycle logging`() {
        val mockFragmentManager = mockk<FragmentManager> {
            every { registerFragmentLifecycleCallbacks(FragmentEvents, true) } just Runs
        }
        val mockActivity = mockk<FragmentActivity> {
            every { supportFragmentManager } returns mockFragmentManager
        }

        ActivityEvents.onActivityCreated(mockActivity, null)

        verify { mockFragmentManager.registerFragmentLifecycleCallbacks(FragmentEvents, true) }
    }

    @Test
    fun `onActivityCreated logs the activity with ON_CREATE lifecycle event`() {
        val mockActivity = mockk<Activity>()

        ActivityEvents.onActivityCreated(mockActivity, null)

        verify { LifecykleLog.logLifecycle(mockActivity, LifecycleEvent.ON_CREATE) }
    }

    @Test
    fun `onActivityCreated logs the activity with ON_CREATE lifecycle event and bundle`() {
        val mockBundle = mockk<Bundle>()
        val mockActivity = mockk<Activity>()

        ActivityEvents.onActivityCreated(mockActivity, mockBundle)

        verify { LifecykleLog.logLifecycle(mockActivity, LifecycleEvent.ON_CREATE, mockBundle) }
    }

    @Test
    fun `onActivityStarted logs the activity with ON_START lifecycle event`() {
        val mockActivity = mockk<Activity>()

        ActivityEvents.onActivityStarted(mockActivity)

        verify { LifecykleLog.logLifecycle(mockActivity, LifecycleEvent.ON_START) }
    }

    @Test
    fun `onActivityResumed logs the activity with ON_RESUME lifecycle event`() {
        val mockActivity = mockk<Activity>()

        ActivityEvents.onActivityResumed(mockActivity)

        verify { LifecykleLog.logLifecycle(mockActivity, LifecycleEvent.ON_RESUME) }
    }

    @Test
    fun `onActivityPaused logs the activity with ON_PAUSE lifecycle event`() {
        val mockActivity = mockk<Activity>()

        ActivityEvents.onActivityPaused(mockActivity)

        verify { LifecykleLog.logLifecycle(mockActivity, LifecycleEvent.ON_PAUSE) }
    }

    @Test
    fun `onActivityStopped logs the activity with ON_STOP lifecycle event`() {
        val mockActivity = mockk<Activity>()

        ActivityEvents.onActivityStopped(mockActivity)

        verify { LifecykleLog.logLifecycle(mockActivity, LifecycleEvent.ON_STOP) }
    }

    @Test
    fun `onActivityDestroyed logs the activity with ON_DESTROY lifecycle event`() {
        val mockActivity = mockk<Activity>()

        ActivityEvents.onActivityDestroyed(mockActivity)

        verify { LifecykleLog.logLifecycle(mockActivity, LifecycleEvent.ON_DESTROY) }
    }

    @Test
    fun `onActivitySaveInstanceState logs the activity with ON_SAVE_INSTANCE_STATE lifecycle event with bundle`() {
        val mockBundle = mockk<Bundle>()
        val mockActivity = mockk<Activity>()

        ActivityEvents.onActivitySaveInstanceState(mockActivity, mockBundle)

        verify {
            LifecykleLog.logLifecycle(
                mockActivity,
                LifecycleEvent.ON_SAVE_INSTANCE_STATE,
                mockBundle
            )
        }
    }
}
