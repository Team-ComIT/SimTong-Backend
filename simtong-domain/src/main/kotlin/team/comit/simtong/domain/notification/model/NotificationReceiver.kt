package team.comit.simtong.domain.notification.model

import java.time.LocalDateTime
import java.util.UUID

/**
 *
 * NotificationReceiver
 *
 * @author kimbeomjin
 * @date 2022/12/29
 * @version 1.1.0
 **/
data class NotificationReceiver(
    val userId: UUID,
    val notificationId: UUID,
    val checkedAt: LocalDateTime
)
