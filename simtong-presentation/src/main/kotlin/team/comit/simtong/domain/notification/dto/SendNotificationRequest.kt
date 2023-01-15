package team.comit.simtong.domain.notification.dto

import team.comit.simtong.domain.notification.dto.request.SendMulticastNotificationData
import team.comit.simtong.domain.notification.dto.request.SendNotificationData
import team.comit.simtong.domain.notification.model.NotificationType
import java.util.UUID
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 *
 * 단일 알림 전송을 요청하는 SendNotificationRequest
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/12/30
 * @version 1.2.5
 **/
data class SendNotificationRequest(

    @field:NotNull
    val userId: UUID,

    @field:NotBlank
    val title: String,

    @field:NotBlank
    val content: String,

    @field:NotNull
    val type: NotificationType,

    val identify: UUID?
) {

    fun toData() = SendNotificationData(
        userId = userId,
        title = title,
        content = content,
        type = type,
        identify = identify
    )
}

/**
 *
 * 다중 알림 전송을 요청하는 SendMultiNotificationRequest
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/12/30
 * @version 1.2.5
 **/
data class SendMultiNotificationRequest(
    val userIds: List<UUID>,

    @field:NotBlank
    val title: String,

    @field:NotBlank
    val content: String,

    @field:NotNull
    val type: NotificationType,

    val identify: UUID?
) {

    fun toData() = SendMulticastNotificationData(
        userIds = userIds,
        title = title,
        content = content,
        type = type,
        identify = identify
    )
}
