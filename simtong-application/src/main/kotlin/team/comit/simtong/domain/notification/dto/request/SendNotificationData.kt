package team.comit.simtong.domain.notification.dto.request

import team.comit.simtong.domain.notification.model.NotificationType
import java.util.UUID

/**
 *
 * 단일 알림 전송 요청 정보를 전달하는 SendNotificationData
 *
 * @author Chokyunghyeon
 * @date 2023/01/13
 * @version 1.2.5
 **/
data class SendNotificationData(
    val userId: UUID,

    val title: String,

    val content: String,

    val type: NotificationType,

    val identify: UUID?
)

/**
 *
 * 다중 알림 전송 요청 정보를 전달하는 SendMulticastNotificationData
 *
 * @author Chokyunghyeon
 * @date 2023/01/13
 * @version 1.2.5
 **/
data class SendMulticastNotificationData(
    val userIds: List<UUID>,

    val title: String,

    val content: String,

    val type: NotificationType,

    val identify: UUID?
)