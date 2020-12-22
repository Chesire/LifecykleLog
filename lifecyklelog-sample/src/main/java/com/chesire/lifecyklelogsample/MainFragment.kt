package com.chesire.lifecyklelogsample

import androidx.fragment.app.Fragment
import com.chesire.lifecyklelog.LifecycleEvent
import com.chesire.lifecyklelog.LogLifecykle

/**
 * [Fragment] to show how to use [LogLifecykle].
 */
@LogLifecykle(
    overrideLogEvents = [
        LifecycleEvent.ON_ACTIVITY_CREATED,
        LifecycleEvent.ON_ATTACH,
        LifecycleEvent.ON_DETACH,
        LifecycleEvent.ON_DESTROY
    ]
)
class MainFragment : Fragment(R.layout.fragment_main) {
    companion object {
        /**
         * Generate a new instance of [MainFragment].
         */
        fun newInstance() = MainFragment()
    }
}
