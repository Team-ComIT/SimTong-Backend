package team.comit.simtong.thirdparty.notification

import com.google.firebase.messaging.ApnsConfig
import com.google.firebase.messaging.Aps
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.MulticastMessage
import com.google.firebase.messaging.Notification
import org.springframework.stereotype.Component
import team.comit.simtong.domain.notification.spi.SendPushMessagePort
import java.util.UUID

/**
 *
 * FCM 메시지를 전송하는 FcmAdapter
 *
 * @author kimbeomjin
 * @date 2022/12/27
 * @version 1.1.0
 **/
@Component
class FcmAdapter : SendPushMessagePort {

    override fun sendMessage(tokens: List<String>, title: String, content: String, identify: UUID?) {
        val multicastMessage = MulticastMessage.builder()
            .addAllTokens(tokens)
            .putData("identify", identify.toString())
            .setNotification(
                Notification.builder()
                    .setTitle(title)
                    .setBody(content)
                    .build()
            )
            .setApnsConfig(
                ApnsConfig.builder()
                    .setAps(Aps.builder().setSound("default").build())
                    .build()
            )
            .build()

        FirebaseMessaging.getInstance().sendMulticastAsync(multicastMessage)
    }

    override fun sendMessage(token: String, title: String, content: String, identify: UUID?) {
        val message = Message.builder()
            .setToken(token)
            .putData("identify", identify.toString())
            .setNotification(
                Notification.builder()
                    .setTitle(title)
                    .setBody(content)
                    .build()
            )
            .setApnsConfig(
                ApnsConfig.builder()
                    .setAps(Aps.builder().setSound("default").build())
                    .build()
            )
            .build()

        FirebaseMessaging.getInstance().sendAsync(message)
    }
}