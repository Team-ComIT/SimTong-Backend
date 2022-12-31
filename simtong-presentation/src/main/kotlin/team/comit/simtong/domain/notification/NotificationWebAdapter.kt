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
 * @version 1.1.0
 **/
@RestController
@RequestMapping("/notifications")
class NotificationWebAdapter(
    private val sendNotificationUseCase: SendNotificationUseCase
) {

    @PostMapping
    fun sendNotification(@RequestBody @Valid request: SendNotificationWebRequest) {
        sendNotificationUseCase.execute(
            userId = request.userId!!,
            title = request.title!!,
            content = request.content!!,
            type = request.type!!.name,
            identify = request.identify
        )
    }

    @PostMapping("/list")
    fun sendMultiNotification(@RequestBody @Valid request: SendMultiNotificationWebRequest) {
        sendNotificationUseCase.execute(
            userIds = request.userIds,
            title = request.title!!,
            content = request.content!!,
            type = request.type!!.name,
            identify = request.identify
        )
    }
}