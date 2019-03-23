package com.chesire.lifecyklelog.flags

import androidx.fragment.app.Fragment

/**
 * Contains a list of lifecycle events available on a [Fragment].
 * Use [eventName] to get its string representation.
 */
enum class FragmentLifecycle(val eventName: String) {
    ON_ATTACH("onAttach"),
    ON_CREATE("onCreate"),
    ON_CREATE_VIEW("onCreateView"),
    ON_ACTIVITY_CREATED("onActivityCreated"),
    ON_START("onStart"),
    ON_RESUME("onResume"),

    ON_PAUSE("onPause"),
    ON_STOP("onStop"),
    ON_DESTROY_VIEW("onDestroyView"),
    ON_DESTROY("onDestroy"),
    ON_DETACH("onDetach"),

    ON_PRE_ATTACHED("onPreAttached"),
    ON_PRE_CREATED("onPreCreated"),
    ON_SAVE_INSTANCE_STATE("onSaveInstanceState")
}
