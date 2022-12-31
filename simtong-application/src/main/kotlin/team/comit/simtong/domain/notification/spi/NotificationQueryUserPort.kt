package team.comit.simtong.domain.notification.spi

import java.util.UUID

/**
 *
 * Notification Domain에서 User Domain에 관한 Query를 요청하는 NotificationQueryUserPort
 *
 * @author kimbeomjin
 * @date 2022/12/30
 * @version 1.1.0
 **/
interface NotificationQueryUserPort {

    fun queryDeviceTokenByUserId(userId: UUID): String?

    fun queryDeviceTokensByUserIds(userIds: List<UUID>): List<String>

}