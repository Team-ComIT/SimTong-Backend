package team.comit.simtong.domain.notification.usecase

import team.comit.simtong.domain.notification.dto.request.SendMulticastNotificationData
import team.comit.simtong.domain.notification.dto.request.SendNotificationData
import team.comit.simtong.domain.notification.model.Notification
import team.comit.simtong.domain.notification.model.NotificationReceiver
import team.comit.simtong.domain.notification.spi.CommandNotificationPort
import team.comit.simtong.domain.notification.spi.NotificationQueryUserPort
import team.comit.simtong.domain.notification.spi.SendPushMessagePort
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 알림 전송을 담당하는 SendNotificationUseCase
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/12/30
 * @version 1.2.5
 **/
@UseCase
class SendNotificationUseCase(
    private val sendPushMessagePort: SendPushMessagePort,
    private val commandNotificationPort: CommandNotificationPort,
    private val queryUserPort: NotificationQueryUserPort
) {

    fun execute(request: SendNotificationData) {
        val deviceToken = queryUserPort.queryDeviceTokenByUserId(request.userId)
            ?: throw UserExceptions.NotFound("디바이스 토큰이 존재하지 않습니다.")

        val notification = commandNotificationPort.saveNotification(
            Notification(
                title = request.title,
                content = request.content,
                type = request.type,
                identify = request.identify
            )
        )

        commandNotificationPort.saveNotificationReceiver(
            NotificationReceiver(
                userId = request.userId,
                notificationId = notification.id
            )
        )

        sendPushMessagePort.sendMessage(deviceToken, request.title, request.content, request.type, request.identify)
    }

    fun execute(request: SendMulticastNotificationData) {
        val deviceTokens = queryUserPort.queryDeviceTokensByUserIds(request.userIds)

        val notification = commandNotificationPort.saveNotification(
            Notification(
                title = request.title,
                content = request.content,
                type = request.type,
                identify = request.identify
            )
        )

        commandNotificationPort.saveAllNotificationReceiver(
            request.userIds.map {
                NotificationReceiver(
                    userId = it,
                    notificationId = notification.id
                )
            }
        )

        sendPushMessagePort.sendMessage(deviceTokens, request.title, request.content, request.type, request.identify)
    }
}