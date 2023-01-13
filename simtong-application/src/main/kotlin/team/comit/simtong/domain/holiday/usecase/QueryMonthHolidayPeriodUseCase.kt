package team.comit.simtong.domain.holiday.usecase

import team.comit.simtong.domain.holiday.dto.response.QueryMonthHolidayPeriodResponse
import team.comit.simtong.domain.holiday.exception.HolidayExceptions
import team.comit.simtong.domain.holiday.spi.HolidayQueryUserPort
import team.comit.simtong.domain.holiday.spi.HolidaySecurityPort
import team.comit.simtong.domain.holiday.spi.QueryHolidayPeriodPort
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.global.annotation.ReadOnlyUseCase

/**
 *
 * 해당 달의 휴무표 작성 기간 조회를 담당하는 QueryMonthHolidayPeriodUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/12/22
 * @version 1.0.0
 **/
@ReadOnlyUseCase
class QueryMonthHolidayPeriodUseCase(
    private val queryHolidayPeriodPort: QueryHolidayPeriodPort,
    private val queryUserPort: HolidayQueryUserPort,
    private val securityPort: HolidaySecurityPort
) {

    fun execute(year: Int, month: Int) : QueryMonthHolidayPeriodResponse {
        val user = queryUserPort.queryUserById(securityPort.getCurrentUserId())
            ?: throw UserExceptions.NotFound()

        val holidayPeriod = queryHolidayPeriodPort.queryHolidayPeriodByYearAndMonthAndSpotId(year, month, user.spotId)
            ?: throw HolidayExceptions.NotFound("휴무표 작성 기간이 등록되지 않았습니다.")

        return holidayPeriod.let {
            QueryMonthHolidayPeriodResponse(
                startAt = it.startAt,
                endAt = it.endAt
            )
        }
    }
}