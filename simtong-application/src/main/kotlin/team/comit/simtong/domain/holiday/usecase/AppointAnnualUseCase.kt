package team.comit.simtong.domain.holiday.usecase

import team.comit.simtong.domain.holiday.exception.HolidayExceptions
import team.comit.simtong.domain.holiday.model.Holiday
import team.comit.simtong.domain.holiday.model.HolidayStatus
import team.comit.simtong.domain.holiday.model.HolidayType
import team.comit.simtong.domain.holiday.spi.CommandHolidayPort
import team.comit.simtong.domain.holiday.spi.HolidayQueryUserPort
import team.comit.simtong.domain.holiday.spi.HolidaySecurityPort
import team.comit.simtong.domain.holiday.spi.QueryHolidayPort
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.global.annotation.UseCase
import java.time.LocalDate

/**
 *
 * 연차 지정을 담당하는 AppointAnnualUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/12/17
 * @version 1.0.0
 **/
@UseCase
class AppointAnnualUseCase(
    private val commandHolidayPort: CommandHolidayPort,
    private val queryHolidayPort: QueryHolidayPort,
    private val queryUserPort: HolidayQueryUserPort,
    private val securityPort: HolidaySecurityPort
) {

    fun execute(date: LocalDate) {
        val user = queryUserPort.queryUserById(securityPort.getCurrentUserId())
            ?: throw UserExceptions.NotFound()

        val countAnnual = queryHolidayPort.countHolidayByYearAndUserIdAndType(date.year, user.id, HolidayType.ANNUAL)

        if (countAnnual >= Holiday.ANNUAL_LEAVE_LIMIT) {
            throw HolidayExceptions.AnnualLeaveLimitExcess()
        }

        commandHolidayPort.save(
            Holiday(
                date = date,
                userId = user.id,
                spotId = user.spotId,
                type = HolidayType.ANNUAL,
                status = HolidayStatus.ANNUAL
            )
        )
    }

}