package team.comit.simtong.domain.notification.model

import team.comit.simtong.global.annotation.Aggregate
import java.time.LocalDateTime
import java.util.UUID

/**
 *
 * 알림 Aggregate의 알림을 받은 수신자를 담당하는 NotificationReceiver
 *
 * @author kimbeomjin
 * @date 2022/12/29
 * @version 1.1.0
 **/
@Aggregate
data class NotificationReceiver(
    val userId: UUID,

    val notificationId: UUID,

    val checkedAt: LocalDateTime? = null
)
