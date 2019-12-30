package com.chesire.lifecyklelog

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

class LifecykleLogTests {
    companion object {
        var logHandlerDefault: LogHandler? = null
        lateinit var logEvents: Array<LifecycleEvent>

        @BeforeClass
        @JvmStatic
        fun getDefaults() {
            logHandlerDefault = LifecykleLog.logHandler
            logEvents = LifecykleLog.logEvents
        }
    }

    @Before
    fun setup() {
        LifecykleLog.logHandler = logHandlerDefault
        LifecykleLog.logEvents = logEvents
    }

    @Test
    fun `logLifecycle with no annotation doesn't execute on handler`() {
        val unannotatedClass = UnannotatedClass()
        val mockHandler = mockk<LogHandler>()
        LifecykleLog.logHandler = mockHandler

        LifecykleLog.logLifecycle(unannotatedClass, LifecycleEvent.ON_ATTACH)

        verify(exactly = 0) { mockHandler.logLifecycleMethod(any(), any(), any()) }
    }

    @Test
    fun `logLifecycle with unsupported default lifecycle event doesn't execute on handler`() {
        val annotatedClass = AnnotatedClass()
        val mockHandler = mockk<LogHandler>()
        LifecykleLog.logHandler = mockHandler

        LifecykleLog.logLifecycle(annotatedClass, LifecycleEvent.ON_SAVE_INSTANCE_STATE)

        verify(exactly = 0) { mockHandler.logLifecycleMethod(any(), any(), any()) }
    }

    @Test
    fun `logLifecycle with overridden default logEvents only executes for that event`() {
        val annotatedClass = AnnotatedClass()
        val mockHandler = mockk<LogHandler> {
            every { logLifecycleMethod(any(), any(), any()) } just Runs
        }
        LifecykleLog.logHandler = mockHandler
        LifecykleLog.logEvents = arrayOf(LifecycleEvent.ON_SAVE_INSTANCE_STATE)

        LifecykleLog.logLifecycle(annotatedClass, LifecycleEvent.ON_ATTACH)
        LifecykleLog.logLifecycle(annotatedClass, LifecycleEvent.ON_CREATE)
        LifecykleLog.logLifecycle(annotatedClass, LifecycleEvent.ON_SAVE_INSTANCE_STATE)
        LifecykleLog.logLifecycle(annotatedClass, LifecycleEvent.ON_DESTROY)

        verify(exactly = 1) { mockHandler.logLifecycleMethod(any(), any(), any()) }
    }

    @Test
    fun `logLifecycle with annotation overrideLogEvents only executes for that event`() {
        val annotatedClass = AnnotatedClassWithLifecycleOverride()
        val mockHandler = mockk<LogHandler> {
            every { logLifecycleMethod(any(), any(), any()) } just Runs
        }
        LifecykleLog.logHandler = mockHandler

        LifecykleLog.logLifecycle(annotatedClass, LifecycleEvent.ON_ATTACH)
        LifecykleLog.logLifecycle(annotatedClass, LifecycleEvent.ON_CREATE)
        LifecykleLog.logLifecycle(annotatedClass, LifecycleEvent.ON_SAVE_INSTANCE_STATE)
        LifecykleLog.logLifecycle(annotatedClass, LifecycleEvent.ON_DESTROY)

        verify(exactly = 1) {
            mockHandler.logLifecycleMethod(
                any(),
                LifecycleEvent.ON_SAVE_INSTANCE_STATE.eventName,
                any()
            )
        }
    }

    @Test
    fun `logLifecycle with defaults and valid logEvent executes on handler`() {
        val annotatedClass = AnnotatedClass()
        val mockHandler = mockk<LogHandler> {
            every { logLifecycleMethod(any(), any(), any()) } just Runs
        }
        LifecykleLog.logHandler = mockHandler

        LifecykleLog.logLifecycle(annotatedClass, LifecycleEvent.ON_ATTACH)

        verify(exactly = 1) {
            mockHandler.logLifecycleMethod(
                AnnotatedClass::class.java.simpleName,
                LifecycleEvent.ON_ATTACH.eventName,
                any()
            )
        }
    }

    @Test
    fun `logLifecycle with annotation name override, uses custom name`() {
        val annotatedClass = AnnotatedClassWithNameOverride()
        val mockHandler = mockk<LogHandler> {
            every { logLifecycleMethod(any(), any(), any()) } just Runs
        }
        LifecykleLog.logHandler = mockHandler

        LifecykleLog.logLifecycle(annotatedClass, LifecycleEvent.ON_ATTACH)

        verify(exactly = 1) {
            mockHandler.logLifecycleMethod(
                "Overridden className",
                LifecycleEvent.ON_ATTACH.eventName,
                any()
            )
        }
    }
}
