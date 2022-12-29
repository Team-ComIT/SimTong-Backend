package team.comit.simtong.domain.notification.model

import java.time.LocalDateTime
import java.util.UUID

/**
 *
 * 알림 Aggregate의 Root를 담당하는 Notification
 *
 * @author kimbeomjin
 * @date 2022/12/29
 * @version 1.1.0
 **/
data class Notification(
    val id: UUID,

    val title: String,

    val content: String,

    val type: NotificationType,

    val identify: UUID?,

    val createdAt: LocalDateTime
)
