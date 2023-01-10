package team.comit.simtong.domain.holiday.usecase

import team.comit.simtong.domain.holiday.exception.HolidayExceptions
import team.comit.simtong.domain.holiday.model.value.HolidayType
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
 * @author kimbeomjin
 * @date 2022/12/04
 * @version 1.2.5
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
            ?: throw HolidayExceptions.NotFound()

        when (holiday.type) {
            HolidayType.HOLIDAY -> if (holiday.isCompleted()) {
                throw HolidayExceptions.CannotChange("결정된 휴무일은 취소할 수 없습니다.")
            }

            HolidayType.ANNUAL -> if (holiday.date <= LocalDate.now()) {
                throw HolidayExceptions.CannotChange("지난 연차는 취소할 수 없습니다.")
            }
        }

        commandHolidayPort.delete(holiday)
    }
}