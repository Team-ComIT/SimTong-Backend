package team.comit.simtong.domain.holiday.usecase

import team.comit.simtong.domain.holiday.dto.IndividualHolidayResponse
import team.comit.simtong.domain.holiday.dto.QueryIndividualHolidaysResponse
import team.comit.simtong.domain.holiday.spi.HolidaySecurityPort
import team.comit.simtong.domain.holiday.spi.QueryHolidayPort
import team.comit.simtong.global.annotation.ReadOnlyUseCase
import java.time.LocalDate

/**
 *
 * 개인 휴무일 조회 요청을 담당하는 QueryIndividualHolidayUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/12/05
 * @version 1.0.0
 **/
@ReadOnlyUseCase
class QueryIndividualHolidayUseCase(
    private val queryHolidayPort: QueryHolidayPort,
    private val securityPort: HolidaySecurityPort
) {

    fun execute(date: LocalDate) : QueryIndividualHolidaysResponse {
        val currentUserId = securityPort.getCurrentUserId()

        val holidays = queryHolidayPort.queryHolidaysByMonthAndUserId(date, currentUserId)

        val response = holidays.map {
            IndividualHolidayResponse(
                date = it.date,
                type = it.type.name
            )
        }

        return QueryIndividualHolidaysResponse(response)
    }

}