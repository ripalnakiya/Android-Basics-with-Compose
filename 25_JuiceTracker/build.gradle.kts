// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    id("androidx.navigation.safeargs") version "2.10.2" apply false
}
buildscript {
    extra.apply {
        set("nav_version", "2.9.3")
        set("room_version", "2.7.2")
        set("arch_lifecycle_version", "2.9.2")
    }
}