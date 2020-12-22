Change Log
==========

## Version 3.1.0
_20-12-22_

* feat: allow logging without annotation
  * Add new configuration option to enable logging for all activities/fragments, without the need for the annotation.

## Version 3.0.0
_19-12-31_

* __[Breaking change]__ feat: pass the bundle through the interface
  * The public API has been updated to also pass the bundle through it now, please see the README for more information.
* refactor: update license type to apache 2.0

## Version 2.1.0
_19-11-07_

* feat: add information for about libraries support
  * LifecykleLog will now be added to the list of libraries used for applications making use of [AboutLibraries](https://github.com/mikepenz/AboutLibraries).

## Version 2.0.0
_19-03-29_

* refactor: change to configuration
  * Change from everything being configured in the `initialize` block, to be more configuration based using properties.

## Version 1.0.0
_19-03-24_

* Initial version.
