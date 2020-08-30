package com.gmail.harsh_chuck.data.textToSpeech

import com.google.gson.annotations.Expose

data class TextToSpeechRequest(
    @Expose
    val c: String = "MP3",
    @Expose
    val r: Int = 0,
    @Expose
    val f: String = "8khz_8bit_mono",
    @Expose
    val src	: String = "Hello, world!",
    @Expose
    val hl: String = "en-us",
    @Expose
    val b64: Boolean = true
)