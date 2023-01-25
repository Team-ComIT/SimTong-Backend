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
    private val userId: UUID?,

    @field:NotBlank
    private val title: String?,

    @field:NotBlank
    private val content: String?,

    @field:NotNull
    private val type: NotificationType?,

    private val identify: UUID?
) {

    fun toData() = SendNotificationData(
        userId = userId!!,
        title = title!!,
        content = content!!,
        type = type!!,
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

    @field:NotNull
    private val userIds: List<UUID>?,

    @field:NotBlank
    private val title: String?,

    @field:NotBlank
    private val content: String?,

    @field:NotNull
    private val type: NotificationType?,

    private val identify: UUID?
) {

    fun toData() = SendMulticastNotificationData(
        userIds = userIds!!,
        title = title!!,
        content = content!!,
        type = type!!,
        identify = identify
    )
}
