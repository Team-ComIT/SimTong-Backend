package team.comit.simtong.domain.notification.model

import team.comit.simtong.global.annotation.Aggregate
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
@Aggregate
data class Notification(
    val id: UUID = UUID(0, 0),

    val title: String,

    val content: String,

    val type: NotificationType,

    val identify: UUID?,

    val createdAt: LocalDateTime = LocalDateTime.now()
)
