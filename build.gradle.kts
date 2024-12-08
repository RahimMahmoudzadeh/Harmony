// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    this.libs.plugins.run{
        alias(android.application) apply false
        alias(kotlin.android) apply false
        alias(ksp) apply false
        alias(kotlin.compose) apply false
        alias(hilt.plugin) apply false
    }
}