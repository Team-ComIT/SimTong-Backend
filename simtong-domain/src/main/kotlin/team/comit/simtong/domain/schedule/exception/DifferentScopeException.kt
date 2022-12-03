package team.comit.simtong.domain.schedule.exception

import team.comit.simtong.domain.schedule.error.ScheduleErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * Different Schedule Scope Error를 발생시키는 DifferentScopeException
 *
 * @author kimbeomjin
 * @date 2022/12/03
 * @version 1.0.0
 **/
class DifferentScopeException private constructor(): BusinessException(ScheduleErrorCode.DIFFERENT_SCHEDULE_SCOPE) {

    companion object {
        @JvmField
        val EXCEPTION = DifferentScopeException()
    }
}