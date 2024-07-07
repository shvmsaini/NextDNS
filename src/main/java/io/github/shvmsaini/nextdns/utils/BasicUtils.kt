package io.github.shvmsaini.nextdns.utils

import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

object BasicUtils {
    private fun String.toHexString(): String {
        val byteArray = this.toByteArray()
        return byteArray.joinToString("") { String.format("%02x", it) }
    }

    fun String.toFavicon(): String {
        return "https://favicons.nextdns.io/hex:${this.toHexString()}@2x.png"
    }

    fun String.toTimeStamp(): String {
        val inputString = this
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' h:mm:ss a")
        val zonedDateTime = ZonedDateTime.parse(inputString, inputFormatter.withZone(ZoneId.of("UTC")))
        val currentTime = ZonedDateTime.now(ZoneId.of("UTC"))

        val timeDifference = ChronoUnit.SECONDS.between(zonedDateTime, currentTime)

        return when {
            timeDifference < 60 -> "$timeDifference seconds ago"
            timeDifference < 3600 -> "${timeDifference / 60} minutes ago"
            timeDifference < 86400 -> "${timeDifference / 3600} hours ago"
            else -> zonedDateTime.withZoneSameInstant(ZoneId.systemDefault()).format(outputFormatter)
        }
    }
}