package team.comit.simtong.domain.holiday.error

import team.comit.simtong.global.error.ErrorProperty

/**
 *
 * 휴무일에 관한 Error Code를 관리하는 HolidayErrorCode
 *
 * @author Chokyunghyeon
 * @date 2022/12/02
 * @version 1.0.0
 **/
enum class HolidayErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {

    ;

    override fun status(): Int = status

    override fun message(): String = message
}