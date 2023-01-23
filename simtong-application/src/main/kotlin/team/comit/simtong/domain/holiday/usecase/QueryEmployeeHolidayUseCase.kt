package team.comit.simtong.domain.holiday.usecase

import team.comit.simtong.domain.holiday.dto.response.QueryEmployeeHolidayResponse
import team.comit.simtong.domain.holiday.model.HolidayQueryType
import team.comit.simtong.domain.holiday.spi.HolidayQueryUserPort
import team.comit.simtong.domain.holiday.spi.HolidaySecurityPort
import team.comit.simtong.domain.holiday.spi.QueryHolidayPort
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.global.annotation.ReadOnlyUseCase
import java.util.UUID

/**
 *
 * 해당하는 지점 직원의 휴무일을 확인하는 QueryEmployeeHolidayUseCase
 *
 * ALL / HOLIDAY / ANNUAL 로 구분해서 필터링
 * teamId를 이용해 파트별로 구분 가능 (null이면 전체)
 * HolidayStatus가 WRITTEN 상태인 것만 조회
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/12/22
 * @version 1.2.5
 **/
@ReadOnlyUseCase
class QueryEmployeeHolidayUseCase(
    private val queryHolidayPort: QueryHolidayPort,
    private val securityPort: HolidaySecurityPort,
    private val queryUserPort: HolidayQueryUserPort
) {

    fun execute(year: Int, month: Int, type: HolidayQueryType, teamId: UUID?): QueryEmployeeHolidayResponse {
        val currentUserId = securityPort.getCurrentUserId()
        val user = queryUserPort.queryUserById(currentUserId) ?: throw UserExceptions.NotFound()

        val employeeHolidays = queryHolidayPort.queryEmployeeHolidaysByYearAndMonthAndTeamId(
            year = year,
            month = month,
            type = type.toHolidayType(),
            spotId = user.spotId,
            teamId = teamId
        )

        return employeeHolidays.map {
            QueryEmployeeHolidayResponse.Holiday(
                date = it.date,
                type = it.type,
                user = QueryEmployeeHolidayResponse.Holiday.Employee(
                    id = it.userId,
                    name = it.userName,
                    team = it.teamName,
                    spot = it.spotName
                )
            )
        }.let(::QueryEmployeeHolidayResponse)
    }
}