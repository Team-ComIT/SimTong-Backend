package team.comit.simtong.domain.schedule.exception

import team.comit.simtong.domain.schedule.error.ScheduleErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * Schedule Not Found Error를 발생시키는 ScheduleNotFoundException
 *
 * @author Chokyunghyeon
 * @date 2022/11/22
 * @version 1.0.0
 **/
class ScheduleNotFoundException private constructor() : BusinessException(ScheduleErrorCode.SCHEDULE_NOT_FOUND) {

    companion object {
        @JvmField
        val EXCEPTION = ScheduleNotFoundException()
    }

}