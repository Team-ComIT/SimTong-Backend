package team.comit.simtong.thirdparty.notification

import com.google.firebase.messaging.ApnsConfig
import com.google.firebase.messaging.Aps
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.MulticastMessage
import com.google.firebase.messaging.Notification
import org.springframework.stereotype.Component
import team.comit.simtong.domain.notification.model.NotificationType
import team.comit.simtong.domain.notification.spi.SendPushMessagePort
import java.util.UUID

/**
 *
 * FCM 메시지를 전송하는 FcmAdapter
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/12/27
 * @version 1.2.3
 **/
@Component
class FcmAdapter : SendPushMessagePort {

    override fun sendMessage(token: String, title: String, content: String, type: NotificationType, identify: UUID?) {
        val message = Message.builder()
            .setToken(token)
            .putData(TYPE, type.name)
            .putData(IDENTIFY, identify.toString())
            .setNotification(
                Notification.builder()
                    .setTitle(title)
                    .setBody(content)
                    .build()
            )
            .setApnsConfig(
                ApnsConfig.builder()
                    .setAps(Aps.builder().setSound(SOUND).build())
                    .build()
            )
            .build()

        FirebaseMessaging.getInstance().sendAsync(message)
    }

    override fun sendMessage(tokens: List<String>, title: String, content: String, type: NotificationType, identify: UUID?) {
        val multicastMessage = MulticastMessage.builder()
            .addAllTokens(tokens)
            .putData(TYPE, type.name)
            .putData(IDENTIFY, identify.toString())
            .setNotification(
                Notification.builder()
                    .setTitle(title)
                    .setBody(content)
                    .build()
            )
            .setApnsConfig(
                ApnsConfig.builder()
                    .setAps(Aps.builder().setSound(SOUND).build())
                    .build()
            )
            .build()

        FirebaseMessaging.getInstance().sendMulticastAsync(multicastMessage)
    }

    companion object {
        private const val TYPE = "type"
        private const val IDENTIFY = "identify"

        private const val SOUND = "default"
    }
}