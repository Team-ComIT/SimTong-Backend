package team.comit.simtong.domain.notification.exception

import team.comit.simtong.global.exception.BusinessException

/**
 *
 * Notification Domain에서 발생하는 Exception을 관리하는 NotificationExceptions
 *
 * @author kimbeomjin
 * @date 2022/12/29
 * @version 1.1.0
 **/
sealed class NotificationExceptions(
    override val status: Int,
    override val message: String,
) : BusinessException(status, message) {

    // 404
    class NotFound(message: String = NOT_FOUND) : NotificationExceptions(404, message)

    companion object {
        private const val NOT_FOUND = "알림을 찾을 수 없습니다."
    }
}
