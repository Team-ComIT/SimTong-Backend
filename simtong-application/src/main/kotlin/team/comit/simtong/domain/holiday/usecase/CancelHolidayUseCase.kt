package team.comit.simtong.domain.holiday.usecase

import team.comit.simtong.domain.holiday.exception.HolidayNotFoundException
import team.comit.simtong.domain.holiday.spi.CommandHolidayPort
import team.comit.simtong.domain.holiday.spi.HolidaySecurityPort
import team.comit.simtong.domain.holiday.spi.QueryHolidayPort
import team.comit.simtong.global.annotation.UseCase
import java.time.LocalDate

/**
 *
 * 휴무일 또는 연차 취소 요청을 담당하는 CancelHolidayUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/12/04
 * @version 1.0.0
 **/
@UseCase
class CancelHolidayUseCase(
    private val commandHolidayPort: CommandHolidayPort,
    private val queryHolidayPort: QueryHolidayPort,
    private val securityPort: HolidaySecurityPort
) {

    fun execute(date: LocalDate) {
        val currentUserId = securityPort.getCurrentUserId()

        val holiday = queryHolidayPort.queryHolidayByDateAndUserId(date, currentUserId)
            ?: throw HolidayNotFoundException.EXCEPTION

        commandHolidayPort.delete(holiday)
    }
}