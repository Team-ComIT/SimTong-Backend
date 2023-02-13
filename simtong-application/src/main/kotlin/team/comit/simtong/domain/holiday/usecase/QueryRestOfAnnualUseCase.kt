package team.comit.simtong.domain.holiday.usecase

import team.comit.simtong.domain.holiday.dto.response.QueryRestOfAnnualResponse
import team.comit.simtong.domain.holiday.model.Holiday
import team.comit.simtong.domain.holiday.model.HolidayType
import team.comit.simtong.domain.holiday.spi.HolidaySecurityPort
import team.comit.simtong.domain.holiday.spi.QueryHolidayPort
import team.comit.simtong.global.annotation.ReadOnlyUseCase

/**
 *
 * 남은 연차 개수 확인을 담당하는 QueryRestOfAnnualUseCase
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/12/20
 * @version 1.2.5
 **/
@ReadOnlyUseCase
class QueryRestOfAnnualUseCase(
    private val queryHolidayPort: QueryHolidayPort,
    private val securityPort: HolidaySecurityPort
) {

    fun execute(year: Int): QueryRestOfAnnualResponse {
        val currentUserId = securityPort.getCurrentUserId()

        val annualCount = queryHolidayPort.countHolidayByYearAndUserIdAndType(year, currentUserId, HolidayType.ANNUAL)

        return Holiday
            .calculateRestOfAnnualCount(annualCount)
            .let(::QueryRestOfAnnualResponse)
    }
}