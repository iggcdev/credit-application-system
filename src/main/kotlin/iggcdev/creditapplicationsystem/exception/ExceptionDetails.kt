package iggcdev.creditapplicationsystem.exception

import java.time.LocalDateTime

data class ExceptionDetails(
    val title: String,
    val timeStamp: LocalDateTime,
    val status: Int,
    val exception: String,
    val details: MutableMap<String,String?>
)
