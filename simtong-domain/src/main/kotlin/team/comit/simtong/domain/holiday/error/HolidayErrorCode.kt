package team.comit.simtong.domain.holiday.error

import team.comit.simtong.global.error.ErrorProperty

/**
 *
 * 휴무일에 관한 Error를 관리하는 HolidayErrorCode
 *
 * @author Chokyunghyeon
 * @date 2022/12/02
 * @version 1.0.0
 **/
enum class HolidayErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {

    HOLIDAY_NOT_FOUND(404, "휴무일을 찾을 수 없습니다."),

    WEEK_HOLIDAY_LIMIT_EXCESS(409, "주 휴무일 지정 한도 초과");

    override fun status(): Int = status

    override fun message(): String = message
}