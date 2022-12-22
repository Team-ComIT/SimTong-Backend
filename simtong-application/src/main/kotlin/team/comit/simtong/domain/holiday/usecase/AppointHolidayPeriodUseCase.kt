package team.comit.simtong.domain.holiday.usecase

import team.comit.simtong.domain.holiday.dto.AppointHolidayPeriodRequest
import team.comit.simtong.domain.holiday.model.HolidayPeriod
import team.comit.simtong.domain.holiday.spi.CommandHolidayPeriodPort
import team.comit.simtong.domain.holiday.spi.HolidayQueryUserPort
import team.comit.simtong.domain.holiday.spi.HolidaySecurityPort
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 휴무표 작성 기간 설정을 담당하는 AppointHolidayPeriodUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/12/22
 * @version 1.0.0
 **/
@UseCase
class AppointHolidayPeriodUseCase(
    private val commandHolidayPeriodPort: CommandHolidayPeriodPort,
    private val queryUserPort: HolidayQueryUserPort,
    private val securityPort: HolidaySecurityPort
) {

    fun execute(request: AppointHolidayPeriodRequest) {
        val user = queryUserPort.queryUserById(securityPort.getCurrentUserId())
            ?: throw UserExceptions.NotFound()

        commandHolidayPeriodPort.save(
            HolidayPeriod(
                year = request.year,
                month = request.month,
                startAt = request.startAt,
                endAt = request.endAt,
                spotId = user.spotId
            )
        )

        // TODO 휴무표 작성 기간 알림
    }
}