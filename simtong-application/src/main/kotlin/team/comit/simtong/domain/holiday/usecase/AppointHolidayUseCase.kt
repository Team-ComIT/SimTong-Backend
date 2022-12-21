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
 * 휴무일 지정을 담당하는 AppointHolidayUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/12/03
 * @version 1.0.0
 **/
@UseCase
class AppointHolidayUseCase(
    private val commandHolidayPort: CommandHolidayPort,
    private val queryHolidayPort: QueryHolidayPort,
    private val queryUserPort: HolidayQueryUserPort,
    private val securityPort: HolidaySecurityPort
) {

    fun execute(date: LocalDate) {
        val user = queryUserPort.queryUserById(securityPort.getCurrentUserId())
            ?: throw UserExceptions.NotFound()

        val countHoliday = queryHolidayPort.countHolidayByWeekAndUserIdAndType(date, user.id, HolidayType.HOLIDAY)

        if (countHoliday >= Holiday.WEEK_HOLIDAY_LIMIT) {
            throw HolidayExceptions.WeekHolidayLimitExcess()
        }

        commandHolidayPort.save(
            Holiday(
                date = date,
                userId = user.id,
                type = HolidayType.HOLIDAY,
                spotId = user.spotId,
                status = HolidayStatus.WRITTEN
            )
        )
    }

}