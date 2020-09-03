package com.gmail.harsh_chuck.helpers

import android.widget.Button

val disableBtn = { btn: Button ->
    btn.alpha = 0.3F
    btn.isClickable = false
}
val enableBtn = { btn: Button ->
    btn.alpha = 1F
    btn.isClickable = true
}