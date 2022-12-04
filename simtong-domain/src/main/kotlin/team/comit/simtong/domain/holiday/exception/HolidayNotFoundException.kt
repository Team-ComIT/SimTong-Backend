package team.comit.simtong.domain.holiday.exception

import team.comit.simtong.domain.holiday.error.HolidayErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * 휴무일을 찾을 수 없음 에러를 발생시키는 HolidayNotFoundException
 *
 * @author Chokyunghyeon
 * @date 2022/12/04
 * @version 1.0.0
 **/
class HolidayNotFoundException private constructor() : BusinessException(HolidayErrorCode.HOLIDAY_NOT_FOUND) {

    companion object {
        @JvmField
        val EXCEPTION = HolidayNotFoundException()
    }
}