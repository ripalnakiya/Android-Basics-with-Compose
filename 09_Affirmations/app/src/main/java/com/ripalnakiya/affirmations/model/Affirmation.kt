package com.ripalnakiya.affirmations.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Affirmation(
    @field:StringRes val stringResourceId: Int,
    @field:DrawableRes val imageResourceId: Int
)