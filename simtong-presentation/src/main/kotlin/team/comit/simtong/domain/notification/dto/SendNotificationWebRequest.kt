package team.comit.simtong.domain.notification.dto

import java.util.UUID

/**
 *
 * 단일 알림 전송을 요청하는 SendNotificationWebRequest
 *
 * @author kimbeomjin
 * @date 2022/12/30
 * @version 1.1.0
 **/
data class SendNotificationWebRequest(
    val userId: UUID,
    val title: String,
    val content: String,
    val type: WebNotificationType,
    val identify: UUID?
)

/**
 *
 * 다중 알림 전송을 요청하는 SendMultiNotificationWebRequest
 *
 * @author kimbeomjin
 * @date 2022/12/30
 * @version 1.1.0
 **/
data class SendMultiNotificationWebRequest(
    val userIds: List<UUID>,
    val title: String,
    val content: String,
    val type: WebNotificationType,
    val identify: UUID?
)
