package team.comit.simtong.domain.notification

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.comit.simtong.domain.notification.dto.SendMultiNotificationRequest
import team.comit.simtong.domain.notification.dto.SendNotificationRequest
import team.comit.simtong.domain.notification.usecase.SendNotificationUseCase
import javax.validation.Valid

/**
 *
 * 알림에 관한 요청을 받는 WebNotificationAdapter
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/12/30
 * @version 1.2.5
 **/
@RestController
@RequestMapping("/notifications")
class WebNotificationAdapter(
    private val sendNotificationUseCase: SendNotificationUseCase
) {

    @PostMapping
    fun sendNotification(@Valid @RequestBody request: SendNotificationRequest) {
        sendNotificationUseCase.execute(request.toData())
    }

    @PostMapping("/list")
    fun sendMultiNotification(@Valid @RequestBody request: SendMultiNotificationRequest) {
        sendNotificationUseCase.execute(request.toData())
    }
}