package team.comit.simtong.domain.holiday.exception

import team.comit.simtong.domain.holiday.error.HolidayErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * 주 휴무일 한도 초과 에러를 발생시키는 WeekHolidayLimitExcessException
 *
 * @author Chokyunghyeon
 * @date 2022/12/03
 * @version 1.0.0
 **/
class WeekHolidayLimitExcessException private constructor() : BusinessException(HolidayErrorCode.WEEK_HOLIDAY_LIMIT_EXCESS) {

    companion object {
        @JvmField
        val EXCEPTION = WeekHolidayLimitExcessException()
    }

}