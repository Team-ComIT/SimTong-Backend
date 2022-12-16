package team.comit.simtong.domain.schedule.exception

import team.comit.simtong.global.exception.BusinessException

/**
 *
 * Schedule Domain에서 발생하는 Exception을 관리하는 ScheduleExceptions
 *
 * @author kimbeomjin
 * @date 2022/12/16
 * @version 1.0.0
 **/
sealed class ScheduleExceptions(
    override val status: Int,
    override val message: String
) : BusinessException(status, message) {

    // 401
    class DifferentScope(message: String = DIFFERENT_SCOPE) : ScheduleExceptions(401, message)

    // 403
    class NotScheduleOwner(message: String = NOT_SCHEDULE_OWNER) : ScheduleExceptions(403, message)

    // 404
    class NotFound(message: String = NOT_FOUND) : ScheduleExceptions(404, message)

    companion object {
        private const val DIFFERENT_SCOPE = "일정의 범위가 다릅니다."
        private const val NOT_SCHEDULE_OWNER = "일정의 소유자가 아닙니다."
        private const val NOT_FOUND = "일정을 찾을 수 없습니다."
    }
}
