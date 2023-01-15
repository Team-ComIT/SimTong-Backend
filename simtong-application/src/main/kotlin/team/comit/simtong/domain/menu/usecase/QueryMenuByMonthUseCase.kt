package team.comit.simtong.domain.menu.usecase

import team.comit.simtong.domain.menu.dto.response.MenuResponse
import team.comit.simtong.domain.menu.spi.MenuQueryUserPort
import team.comit.simtong.domain.menu.spi.MenuSecurityPort
import team.comit.simtong.domain.menu.spi.QueryMenuPort
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.global.annotation.ReadOnlyUseCase
import java.time.LocalDate

/**
 *
 * Menu 조회 기능을 담당하는 QueryMenuByMonthUseCase
 *
 * @author kimbeomjin
 * @date 2022/09/21
 * @version 1.0.0
 **/
@ReadOnlyUseCase
class QueryMenuByMonthUseCase(
    private val queryMenuPort: QueryMenuPort,
    private val queryUserPort: MenuQueryUserPort,
    private val menuSecurityPort: MenuSecurityPort
) {

    fun execute(startAt: LocalDate, endAt: LocalDate): MenuResponse {
        val currentUserId = menuSecurityPort.getCurrentUserId()
        val user = queryUserPort.queryUserById(currentUserId) ?: throw UserExceptions.NotFound()

        val menu = queryMenuPort.queryMenusByPeriodAndSpotId(startAt, endAt, user.spotId)
        val result = menu.map { MenuResponse.MenuElement(it.date, it.meal) }

        return MenuResponse(result)
    }
}