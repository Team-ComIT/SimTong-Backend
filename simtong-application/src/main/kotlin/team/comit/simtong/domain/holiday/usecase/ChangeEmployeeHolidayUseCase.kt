package team.comit.simtong.domain.holiday.usecase

import team.comit.simtong.domain.holiday.exception.HolidayExceptions
import team.comit.simtong.domain.holiday.model.HolidayType
import team.comit.simtong.domain.holiday.spi.CommandHolidayPort
import team.comit.simtong.domain.holiday.spi.HolidayQueryUserPort
import team.comit.simtong.domain.holiday.spi.HolidaySecurityPort
import team.comit.simtong.domain.holiday.spi.QueryHolidayPort
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.global.annotation.UseCase
import java.time.LocalDate
import java.util.UUID

/**
 *
 * 지점 직원의 휴무일을 변경하는 ChangeEmployeeHolidayUseCase
 * 기존 휴무일 삭제, 새로운 휴무일 생성 (PK라 변경할 수 없음)
 *
 * @author kimbeomjin
 * @date 2022/12/23
 * @version 1.2.5
 **/
@UseCase
class ChangeEmployeeHolidayUseCase(
    private val queryHolidayPort: QueryHolidayPort,
    private val commandHolidayPort: CommandHolidayPort,
    private val securityPort: HolidaySecurityPort,
    private val queryUserPort: HolidayQueryUserPort
) {

    fun execute(beforeDate: LocalDate, userId: UUID, afterDate: LocalDate) {
        val currentUserId = securityPort.getCurrentUserId()
        val admin = queryUserPort.queryUserById(currentUserId) ?: throw UserExceptions.NotFound()

        val holiday = queryHolidayPort.queryHolidayByDateAndUserId(beforeDate, userId)
            ?: throw HolidayExceptions.NotFound()

        if (!holiday.isSameSpot(admin.spotId)) {
            throw HolidayExceptions.CannotChange("같은 지점 직원의 휴무일만 변경할 수 있습니다.")
        }

        val holidayCount = queryHolidayPort.countHolidayByWeekAndUserIdAndType(afterDate, userId, HolidayType.HOLIDAY)

        commandHolidayPort.save(
            holiday.change(holidayCount, afterDate)
        )
        commandHolidayPort.delete(holiday)
    }
}