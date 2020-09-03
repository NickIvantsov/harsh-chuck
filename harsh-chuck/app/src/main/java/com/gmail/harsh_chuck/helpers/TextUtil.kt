package com.gmail.harsh_chuck.helpers

import android.widget.TextView

/**
 * функция устанавливает текст текстовому полю
 */
val setText: (TextView, String) -> Unit =
    { textView: TextView, text: String -> textView.text = text }

