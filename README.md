# LifecykleLog  

> Library to easily log out Android lifecycle methods for Activities and Fragments.

[![Build Status](https://app.bitrise.io/app/fafeb502b668e0c5/status.svg?token=AqJ-aneNiSWPCyEgIdaqpA&branch=master)](https://app.bitrise.io/app/fafeb502b668e0c5)
[![Download](https://api.bintray.com/packages/chesire/LifecykleLog/lifecyklelog/images/download.svg) ](https://bintray.com/chesire/LifecykleLog/lifecyklelog/_latestVersion)

## Installation

Gradle - add the following line to your `build.gradle`

```groovy
implementation 'com.chesire:lifecyklelog:{version}'
```

## Usage example

Initialize in your application class.

```kotlin
class ApplicationOverride : Application() {
    override fun onCreate() {
        super.onCreate()
        LifecykleLog.initialize(this)
    }
}
```

Add the `@LogLifecykle` annotation to the Activity or Fragment that the lifecycle methods should be logged for.

```kotlin
@LogLifecykle
class MainActivity : AppCompatActivity() { ...

@LogLifecykle
class MainFragment : Fragment() { ...
```

Then lifecycle events will be logged out in logcat.

```
D/Lifecykle: MainActivity ⇀ onStart
D/Lifecykle: MainFragment ⇀ onAttach
D/Lifecykle: MainFragment ⇀ onCreate
D/Lifecykle: MainFragment ⇀ onCreateView
D/Lifecykle: MainFragment ⇀ onActivityCreated
D/Lifecykle: MainFragment ⇀ onStart
D/Lifecykle: MainActivity ⇀ onResume
D/Lifecykle: MainFragment ⇀ onResume
D/Lifecykle: MainActivity ⇀ onPause
D/Lifecykle: MainFragment ⇀ onPause
D/Lifecykle: MainActivity ⇀ onStop
D/Lifecykle: MainFragment ⇀ onStop
```

## Configuration

### Logging mechanism
By default LogLifecykle will output to `Log.d` with a tag of `Lifecykle`, to override this behaviour pass an implementation into the `LifecykleLog.logHandler`.

```kotlin
LifecykleLog.logHandler = LogHandler { clazz, lifecycleEvent ->
    Log.e(clazz, lifecycleEvent) 
}
```

This can allow you to use other logging frameworks such as Timber.

```kotlin
LifecykleLog.logHandler = LogHandler { clazz, lifecycleEvent ->
    Timber.i("$clazz -> $lifecycleEvent")
}
```

### Lifecycle methods
To customise which lifecycle methods are logged out, an array of the `LifecycleEvent` enum can be passed into  `LifecykleLog.logEvents`, this can also be done with the `@LogLifecykle` annotation.

```kotlin
LifecykleLog.logEvents = arrayOf(
    LifecycleEvent.ON_CREATE, 
    LifecycleEvent.ON_DESTROY
)

@LogLifecykle(overrideLogEvents = [LifecycleEvent.ON_START])
class MainActivity : AppCompatActivity() {

@LogLifecykle(overrideLogEvents = [LifecycleEvent.ON_ACTIVITY_CREATED, LifecycleEvent.ON_ATTACH])
class MainFragment : Fragment() {
```
If `logEvents` is provided to the `LifecykleLog` then it will override the defaults.  
If `overrideLogEvents` is provided on the annotation, **only** the methods that are provided in this will be logged out.

### Class name
To customise the class name that is logged out, a new name can be provided to the annotation.

```kotlin
@LogLifecykle(className = "MainActivity")
class MainActivity : AppCompatActivity() {

@LogLifecykle(className = "MaybeMainFragment")
class MainFragment : Fragment() {
```

This can be useful if ProGuard strips out the class names and you really need to see them in the logs. By default the name will be pulled from the objects `class.java.simpleName`.

_For more examples and usage, please refer to the [sample](https://github.com/Chesire/LifecykleLog/tree/master/lifecyklelog-sample)._


## Release History

* 2.0.0
	* Refactor to be easier to configure, stripping the initialize method down to just taking the Application object.
* 1.0.0
    * Initial version


## Contributing
Please read [CONTRIBUTING.md](https://github.com/Chesire/LifecykleLog/blob/master/CONTRIBUTING.md) for details on how to contribute.


## License

MIT - See [LICENSE](https://github.com/Chesire/LifecykleLog/blob/master/LINCENSE) for more information.
