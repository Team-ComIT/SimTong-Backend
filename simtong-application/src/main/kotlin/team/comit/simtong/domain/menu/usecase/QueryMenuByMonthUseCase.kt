package team.comit.simtong.domain.menu.usecase

import team.comit.simtong.domain.menu.dto.MenuResponse
import team.comit.simtong.domain.menu.spi.QueryMenuPort
import team.comit.simtong.global.annotation.ReadOnlyUseCase
import java.time.LocalDate
import java.util.*

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
    private val queryMenuPort: QueryMenuPort
) {

    fun execute(today: LocalDate, spotId: UUID): MenuResponse {
        val menu = queryMenuPort.queryMenuByMonth(today.year, today.monthValue, spotId)
        val result = menu.map { MenuResponse.MenuElement(it.date, it.meal) }

        return MenuResponse(result)
    }

}