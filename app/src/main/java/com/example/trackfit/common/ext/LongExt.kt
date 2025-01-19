package com.example.trackfit.common.ext

import java.text.SimpleDateFormat
import java.util.Date

fun Long.toDateTimeString(): String {
    val date = Date(this)
    val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
    return format.format(date)
}