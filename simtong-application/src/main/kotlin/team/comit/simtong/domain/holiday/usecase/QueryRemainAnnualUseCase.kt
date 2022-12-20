package team.comit.simtong.domain.holiday.usecase

import team.comit.simtong.domain.holiday.model.Holiday
import team.comit.simtong.domain.holiday.model.HolidayType
import team.comit.simtong.domain.holiday.spi.HolidaySecurityPort
import team.comit.simtong.domain.holiday.spi.QueryHolidayPort
import team.comit.simtong.global.annotation.ReadOnlyUseCase

/**
 *
 * 남은 연차 개수 확인을 담당하는 QueryRemainAnnualUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/12/20
 * @version 1.0.0
 **/
@ReadOnlyUseCase
class QueryRemainAnnualUseCase(
    private val queryHolidayPort: QueryHolidayPort,
    private val securityPort: HolidaySecurityPort
) {

    fun execute(year: Int): Long {
        val currentUserId = securityPort.getCurrentUserId()

        val countAnnual = queryHolidayPort.countHolidayByYearAndUserIdAndType(year, currentUserId, HolidayType.ANNUAL)

        return Holiday.ANNUAL_LEAVE_LIMIT - countAnnual
    }

}