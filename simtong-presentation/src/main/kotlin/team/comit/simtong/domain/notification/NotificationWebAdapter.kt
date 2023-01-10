package team.comit.simtong.domain.notification

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.comit.simtong.domain.notification.dto.SendMultiNotificationWebRequest
import team.comit.simtong.domain.notification.dto.SendNotificationWebRequest
import team.comit.simtong.domain.notification.usecase.SendNotificationUseCase
import javax.validation.Valid

/**
 *
 * 알림에 관한 요청을 받는 NotificationWebAdapter
 *
 * @author kimbeomjin
 * @date 2022/12/30
 * @version 1.2.5
 **/
@RestController
@RequestMapping("/notifications")
class NotificationWebAdapter(
    private val sendNotificationUseCase: SendNotificationUseCase
) {

    @PostMapping
    fun sendNotification(@Valid @RequestBody request: SendNotificationWebRequest) {
        sendNotificationUseCase.execute(
            userId = request.userId,
            title = request.title,
            content = request.content,
            type = request.type,
            identify = request.identify
        )
    }

    @PostMapping("/list")
    fun sendMultiNotification(@Valid @RequestBody request: SendMultiNotificationWebRequest) {
        sendNotificationUseCase.execute(
            userIds = request.userIds,
            title = request.title,
            content = request.content,
            type = request.type,
            identify = request.identify
        )
    }
}