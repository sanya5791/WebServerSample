package util

import java.text.SimpleDateFormat
import java.util.*

class LoggerImpl : Logger {
    private val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")

    override fun out(message: String) {
        val currentDate = sdf.format(Date())
        println("$currentDate: $message")
    }

}