package com.chesire.lifecyklelogsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_main, container, false)

    companion object {
        /**
         * Generate a new instance of [MainFragment].
         */
        fun newInstance() = MainFragment()
    }
}
