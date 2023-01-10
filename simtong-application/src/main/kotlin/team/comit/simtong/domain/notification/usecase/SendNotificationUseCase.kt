package team.comit.simtong.domain.notification.usecase

import team.comit.simtong.domain.notification.model.Notification
import team.comit.simtong.domain.notification.model.NotificationReceiver
import team.comit.simtong.domain.notification.model.value.NotificationType
import team.comit.simtong.domain.notification.spi.CommandNotificationPort
import team.comit.simtong.domain.notification.spi.NotificationQueryUserPort
import team.comit.simtong.domain.notification.spi.SendPushMessagePort
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.global.annotation.UseCase
import java.util.UUID
import kotlin.collections.List

/**
 *
 * 알림 전송을 담당하는 SendNotificationUseCase
 *
 * @author kimbeomjin
 * @date 2022/12/30
 * @version 1.1.0
 **/
@UseCase
class SendNotificationUseCase(
    private val sendPushMessagePort: SendPushMessagePort,
    private val commandNotificationPort: CommandNotificationPort,
    private val queryUserPort: NotificationQueryUserPort
) {

    fun execute(userId: UUID, title: String, content: String, type: String, identify: UUID?) {
        val deviceToken = queryUserPort.queryDeviceTokenByUserId(userId)
            ?: throw UserExceptions.NotFound("디바이스 토큰이 존재하지 않습니다.")

        val notification = commandNotificationPort.saveNotification(
            Notification(
                title = title,
                content = content,
                type = NotificationType.valueOf(type),
                identify = identify
            )
        )

        commandNotificationPort.saveNotificationReceiver(
            NotificationReceiver(
                userId = userId,
                notificationId = notification.id
            )
        )

        sendPushMessagePort.sendMessage(deviceToken, title, content, NotificationType.valueOf(type), identify)
    }

    fun execute(userIds: List<UUID>, title: String, content: String, type: String, identify: UUID?) {
        val deviceTokens = queryUserPort.queryDeviceTokensByUserIds(userIds)

        val notification = commandNotificationPort.saveNotification(
            Notification(
                title = title,
                content = content,
                type = NotificationType.valueOf(type),
                identify = identify
            )
        )

        commandNotificationPort.saveAllNotificationReceiver(
            userIds.map {
                NotificationReceiver(
                    userId = it,
                    notificationId = notification.id
                )
            }
        )

        sendPushMessagePort.sendMessage(deviceTokens, title, content, NotificationType.valueOf(type), identify)
    }
}