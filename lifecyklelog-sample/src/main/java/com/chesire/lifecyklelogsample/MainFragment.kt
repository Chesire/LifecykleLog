package com.chesire.lifecyklelogsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chesire.lifecyklelog.LogLifecykle

/**
 * [Fragment] to show how to use [LogLifecykle].
 */
@LogLifecykle
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
