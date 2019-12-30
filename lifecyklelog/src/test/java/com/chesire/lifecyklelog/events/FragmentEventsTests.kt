package com.chesire.lifecyklelog.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.chesire.lifecyklelog.LifecycleEvent
import com.chesire.lifecyklelog.LifecykleLog
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkObject
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test

class FragmentEventsTests {
    @Before
    fun setup() = mockkObject(LifecykleLog)

    @After
    fun teardown() = unmockkObject(LifecykleLog)

    @Test
    fun `onFragmentAttached logs the fragment with ON_ATTACH lifecycle event`() {
        val mockFragment = mockk<Fragment>()

        FragmentEvents.onFragmentAttached(mockk(), mockFragment, mockk())

        verify { LifecykleLog.logLifecycle(mockFragment, LifecycleEvent.ON_ATTACH) }
    }

    @Test
    fun `onFragmentCreated logs the fragment with ON_CREATE lifecycle event`() {
        val mockFragment = mockk<Fragment>()

        FragmentEvents.onFragmentCreated(mockk(), mockFragment, null)

        verify { LifecykleLog.logLifecycle(mockFragment, LifecycleEvent.ON_CREATE) }
    }

    @Test
    fun `onFragmentCreated logs the fragment with ON_CREATE lifecycle event and bundle`() {
        val mockBundle = mockk<Bundle>()
        val mockFragment = mockk<Fragment>()

        FragmentEvents.onFragmentCreated(mockk(), mockFragment, mockBundle)

        verify { LifecykleLog.logLifecycle(mockFragment, LifecycleEvent.ON_CREATE, mockBundle) }
    }

    @Test
    fun `onFragmentViewCreated logs the fragment with ON_CREATE_VIEW lifecycle event`() {
        val mockFragment = mockk<Fragment>()

        FragmentEvents.onFragmentViewCreated(mockk(), mockFragment, mockk(), null)

        verify { LifecykleLog.logLifecycle(mockFragment, LifecycleEvent.ON_CREATE_VIEW) }
    }

    @Test
    fun `onFragmentViewCreated logs the fragment with ON_CREATE_VIEW lifecycle event and bundle`() {
        val mockBundle = mockk<Bundle>()
        val mockFragment = mockk<Fragment>()

        FragmentEvents.onFragmentViewCreated(mockk(), mockFragment, mockk(), mockBundle)

        verify {
            LifecykleLog.logLifecycle(
                mockFragment,
                LifecycleEvent.ON_CREATE_VIEW,
                mockBundle
            )
        }
    }

    @Test
    fun `onFragmentActivityCreated logs the fragment with ON_ACTIVITY_CREATED lifecycle event`() {
        val mockFragment = mockk<Fragment>()

        FragmentEvents.onFragmentActivityCreated(mockk(), mockFragment, null)

        verify { LifecykleLog.logLifecycle(mockFragment, LifecycleEvent.ON_ACTIVITY_CREATED) }
    }

    @Test
    fun `onFragmentActivityCreated logs the fragment with ON_ACTIVITY_CREATED lifecycle event and bundle`() {
        val mockBundle = mockk<Bundle>()
        val mockFragment = mockk<Fragment>()

        FragmentEvents.onFragmentActivityCreated(mockk(), mockFragment, mockBundle)

        verify {
            LifecykleLog.logLifecycle(
                mockFragment,
                LifecycleEvent.ON_ACTIVITY_CREATED,
                mockBundle
            )
        }
    }

    @Test
    fun `onFragmentStarted logs the fragment with ON_START lifecycle event`() {
        val mockFragment = mockk<Fragment>()

        FragmentEvents.onFragmentStarted(mockk(), mockFragment)

        verify { LifecykleLog.logLifecycle(mockFragment, LifecycleEvent.ON_START) }
    }

    @Test
    fun `onFragmentResumed logs the fragment with ON_RESUME lifecycle event`() {
        val mockFragment = mockk<Fragment>()

        FragmentEvents.onFragmentResumed(mockk(), mockFragment)

        verify { LifecykleLog.logLifecycle(mockFragment, LifecycleEvent.ON_RESUME) }
    }

    @Test
    fun `onFragmentPaused logs the fragment with ON_PAUSE lifecycle event`() {
        val mockFragment = mockk<Fragment>()

        FragmentEvents.onFragmentPaused(mockk(), mockFragment)

        verify { LifecykleLog.logLifecycle(mockFragment, LifecycleEvent.ON_PAUSE) }
    }

    @Test
    fun `onFragmentStopped logs the fragment with ON_STOP lifecycle event`() {
        val mockFragment = mockk<Fragment>()

        FragmentEvents.onFragmentStopped(mockk(), mockFragment)

        verify { LifecykleLog.logLifecycle(mockFragment, LifecycleEvent.ON_STOP) }
    }

    @Test
    fun `onFragmentViewDestroyed logs the fragment with ON_DESTROY_VIEW lifecycle event`() {
        val mockFragment = mockk<Fragment>()

        FragmentEvents.onFragmentViewDestroyed(mockk(), mockFragment)

        verify { LifecykleLog.logLifecycle(mockFragment, LifecycleEvent.ON_DESTROY_VIEW) }
    }

    @Test
    fun `onFragmentDestroyed logs the fragment with ON_DESTROY lifecycle event`() {
        val mockFragment = mockk<Fragment>()

        FragmentEvents.onFragmentDestroyed(mockk(), mockFragment)

        verify { LifecykleLog.logLifecycle(mockFragment, LifecycleEvent.ON_DESTROY) }
    }

    @Test
    fun `onFragmentDetached logs the fragment with ON_DETACH lifecycle event`() {
        val mockFragment = mockk<Fragment>()

        FragmentEvents.onFragmentDetached(mockk(), mockFragment)

        verify { LifecykleLog.logLifecycle(mockFragment, LifecycleEvent.ON_DETACH) }
    }

    @Test
    fun `onFragmentPreAttached logs the fragment with ON_PRE_ATTACHED lifecycle event`() {
        val mockFragment = mockk<Fragment>()

        FragmentEvents.onFragmentPreAttached(mockk(), mockFragment, mockk())

        verify { LifecykleLog.logLifecycle(mockFragment, LifecycleEvent.ON_PRE_ATTACHED) }
    }

    @Test
    fun `onFragmentPreCreated logs the fragment with ON_PRE_CREATED lifecycle event`() {
        val mockFragment = mockk<Fragment>()

        FragmentEvents.onFragmentPreCreated(mockk(), mockFragment, null)

        verify { LifecykleLog.logLifecycle(mockFragment, LifecycleEvent.ON_PRE_CREATED) }
    }

    @Test
    fun `onFragmentPreCreated logs the fragment with ON_PRE_CREATED lifecycle event and bundle`() {
        val mockBundle = mockk<Bundle>()
        val mockFragment = mockk<Fragment>()

        FragmentEvents.onFragmentPreCreated(mockk(), mockFragment, mockBundle)

        verify {
            LifecykleLog.logLifecycle(
                mockFragment,
                LifecycleEvent.ON_PRE_CREATED,
                mockBundle
            )
        }
    }

    @Test
    fun `onFragmentSaveInstanceState logs the fragment with ON_SAVE_INSTANCE_STATE lifecycle event and bundle`() {
        val mockBundle = mockk<Bundle>()
        val mockFragment = mockk<Fragment>()

        FragmentEvents.onFragmentSaveInstanceState(mockk(), mockFragment, mockBundle)

        verify {
            LifecykleLog.logLifecycle(
                mockFragment,
                LifecycleEvent.ON_SAVE_INSTANCE_STATE,
                mockBundle
            )
        }
    }
}
