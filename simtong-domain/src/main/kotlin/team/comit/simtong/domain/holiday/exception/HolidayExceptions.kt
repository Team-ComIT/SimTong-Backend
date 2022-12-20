package team.comit.simtong.domain.holiday.exception

import team.comit.simtong.global.exception.BusinessException

/**
 *
 * Holiday Domain에서 발생하는 Exception을 관리하는 HolidayExceptions
 *
 * @author kimbeomjin
 * @date 2022/12/16
 * @version 1.0.0
 **/
sealed class HolidayExceptions(
    override val status: Int,
    override val message: String
) : BusinessException(status, message) {

    // 404
    class NotFound(message: String = NOT_FOUND) : HolidayExceptions(404, message)

    // 429
    class WeekHolidayLimitExcess(message: String = WEEK_HOLIDAY_LIMIT_EXCESS) : HolidayExceptions(429, message)
    class AnnualLeaveLimitExcess(message: String = ANNUAL_LEAVE_LIMIT_EXCESS) : HolidayExceptions(429, message)

    companion object {
        private const val NOT_FOUND = "휴무일을 찾을 수 없습니다."
        private const val WEEK_HOLIDAY_LIMIT_EXCESS = "주 휴무일 지정 한도 초과"
        private const val ANNUAL_LEAVE_LIMIT_EXCESS = "연차 지정 한도 초과"
    }
}
