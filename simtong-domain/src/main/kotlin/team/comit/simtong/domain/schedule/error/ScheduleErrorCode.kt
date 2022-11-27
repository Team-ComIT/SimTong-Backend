package team.comit.simtong.domain.schedule.error

import team.comit.simtong.global.error.ErrorProperty

/**
 *
 * 일정에 대해 발생하는 Error를 관리하는 ScheduleErrorCode
 *
 * @author Chokyunghyeon
 * @date 2022/11/21
 * @version 1.0.0
 **/
enum class ScheduleErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {

    // 403
    NOT_SCHEDULE_OWNER(403, "일정의 소유자가 아님"),

    // 404
    SCHEDULE_NOT_FOUND(404, "일정을 찾을 수 없음");

    override fun status() = status

    override fun message() = message
}