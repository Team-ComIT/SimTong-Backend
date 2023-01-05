package team.comit.simtong.domain.notification.spi

import team.comit.simtong.domain.notification.model.Notification
import team.comit.simtong.domain.notification.model.NotificationReceiver

/**
 *
 * Notification Domain에 관한 명령을 하는 CommandNotificationPort
 *
 * @author kimbeomjin
 * @date 2022/12/30
 * @version 1.2.3
 **/
interface CommandNotificationPort {

    fun saveNotification(notification: Notification): Notification

    fun saveNotificationReceiver(notificationReceiver: NotificationReceiver): NotificationReceiver

    fun saveAllNotificationReceiver(notificationReceivers: List<NotificationReceiver>)

}