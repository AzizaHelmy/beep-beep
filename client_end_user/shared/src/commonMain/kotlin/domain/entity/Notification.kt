package domain.entity

data class Notification(
    val id: String,
    val title: String,
    val body: String,
    val date: Long,
    val time: Time,
    val userId: String,
    val topic: String,
)
