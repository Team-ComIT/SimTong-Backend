package team.comit.simtong.domain.notification.spi

import team.comit.simtong.domain.notification.model.NotificationType
import java.util.UUID

/**
 *
 * 푸시 알림 전송을 요청하는 SendPushMessagePort
 *
 * @author kimbeomjin
 * @date 2022/12/30
 * @version 1.1.0
 **/
interface SendPushMessagePort {
    fun sendMessage(token: String, title: String, content: String, type: NotificationType, identify: UUID?)

    fun sendMessage(tokens: List<String>, title: String, content: String, type: NotificationType, identify: UUID?)

}