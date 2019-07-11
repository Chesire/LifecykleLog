package com.chesire.lifecyklelog

internal class UnannotatedClass

@LogLifecykle
internal class AnnotatedClass

@LogLifecykle("Overridden className")
internal class AnnotatedClassWithNameOverride

@LogLifecykle(overrideLogEvents = [LifecycleEvent.ON_SAVE_INSTANCE_STATE])
internal class AnnotatedClassWithLifecycleOverride
