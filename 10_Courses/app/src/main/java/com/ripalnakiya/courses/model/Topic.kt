package com.ripalnakiya.courses.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @field:StringRes val stringResourceId: Int,
    val lessons: Int,
    @field:DrawableRes val imageResourceId: Int,
)
