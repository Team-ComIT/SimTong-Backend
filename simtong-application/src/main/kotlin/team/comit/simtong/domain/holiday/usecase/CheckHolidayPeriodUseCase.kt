package team.comit.simtong.domain.holiday.usecase

import team.comit.simtong.domain.holiday.exception.HolidayExceptions
import team.comit.simtong.domain.holiday.spi.HolidayQueryUserPort
import team.comit.simtong.domain.holiday.spi.HolidaySecurityPort
import team.comit.simtong.domain.holiday.spi.QueryHolidayPeriodPort
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.global.annotation.ReadOnlyUseCase
import java.time.LocalDate

/**
 *
 * 휴무일 작성 기간인지 확인을 담당하는 CheckHolidayPeriodUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/12/22
 * @version 1.2.3
 **/
@ReadOnlyUseCase
class CheckHolidayPeriodUseCase(
    private val queryHolidayPeriodPort: QueryHolidayPeriodPort,
    private val queryUserPort: HolidayQueryUserPort,
    private val securityPort: HolidaySecurityPort
) {

    fun execute() {
        val user = queryUserPort.queryUserById(securityPort.getCurrentUserId())
            ?: throw UserExceptions.NotFound()

        if (!queryHolidayPeriodPort.existsHolidayPeriodByWithinPeriodAndSpotId(LocalDate.now(), user.spotId)) {
            throw HolidayExceptions.NotWritablePeriod()
        }
    }

}