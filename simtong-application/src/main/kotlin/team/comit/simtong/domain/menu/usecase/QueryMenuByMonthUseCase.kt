package team.comit.simtong.domain.menu.usecase

import team.comit.simtong.domain.menu.model.Menu
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
 * @version 1.2.5
 **/
@ReadOnlyUseCase
class QueryMenuByMonthUseCase(
    private val queryMenuPort: QueryMenuPort,
    private val queryUserPort: MenuQueryUserPort,
    private val menuSecurityPort: MenuSecurityPort
) {

    fun execute(startAt: LocalDate, endAt: LocalDate): List<Menu> {
        val currentUserId = menuSecurityPort.getCurrentUserId()
        val user = queryUserPort.queryUserById(currentUserId) ?: throw UserExceptions.NotFound()

        return queryMenuPort.queryMenusByPeriodAndSpotId(startAt, endAt, user.spotId)
    }
}