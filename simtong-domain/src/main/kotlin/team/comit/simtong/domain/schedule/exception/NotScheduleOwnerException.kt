package team.comit.simtong.domain.schedule.exception

import team.comit.simtong.domain.schedule.error.ScheduleErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * Not Schedule Owner Error를 발생시키는 NotScheduleOwnerException
 *
 * @author Chokyunghyeon
 * @date 2022/11/27
 * @version 1.0.0
 **/
class NotScheduleOwnerException private constructor() : BusinessException(ScheduleErrorCode.NOT_SCHEDULE_OWNER) {

    companion object {
        @JvmField
        val EXCEPTION = NotScheduleOwnerException()
    }
}