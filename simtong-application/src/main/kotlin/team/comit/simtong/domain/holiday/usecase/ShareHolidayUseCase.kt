package team.comit.simtong.domain.holiday.usecase

import team.comit.simtong.domain.holiday.model.HolidayType
import team.comit.simtong.domain.holiday.spi.CommandHolidayPort
import team.comit.simtong.domain.holiday.spi.HolidayQueryUserPort
import team.comit.simtong.domain.holiday.spi.HolidaySecurityPort
import team.comit.simtong.domain.holiday.spi.QueryHolidayPort
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 관리자가 조율된 휴무표를 공유하는 ShareHolidayUseCase
 *
 * @author kimbeomjin
 * @date 2022/12/21
 * @version 1.2.5
 **/
@UseCase
class ShareHolidayUseCase(
    private val queryHolidayPort: QueryHolidayPort,
    private val commandHolidayPort: CommandHolidayPort,
    private val securityPort: HolidaySecurityPort,
    private val queryUserPort: HolidayQueryUserPort
) {

    fun execute(year: Int, month: Int) {
        val currentUserId = securityPort.getCurrentUserId()
        val user = queryUserPort.queryUserById(currentUserId) ?: throw UserExceptions.NotFound()

        val holidays = queryHolidayPort.queryHolidaysByYearAndMonthAndSpotIdAndType(
            year, month, user.spotId, HolidayType.HOLIDAY
        )

        val completedHolidays = holidays.map {
            it.share()
        }

        commandHolidayPort.saveAll(completedHolidays)
    }
}