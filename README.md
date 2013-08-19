Robolectric FEST
================

A set of FEST assertion helpers geared toward testing Android with Robolectric.

This is an extention of [fest-android](https://github.com/square/fest-android) that adds features specific to [Robolectric](https://github.com/robolectric/robolectric) that could not be a part ofthe fest-android library.

See fest-android's [README](https://github.com/square/fest-android/blob/master/README.md) for usage/extension instructions.

To get started writing tests add the following import:

```java
import static org.fest.assertions.api.ROBOLECTRIC.assertThat;
```