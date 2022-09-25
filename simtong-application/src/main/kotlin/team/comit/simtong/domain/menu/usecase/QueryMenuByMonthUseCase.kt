package team.comit.simtong.domain.menu.usecase

import team.comit.simtong.domain.menu.dto.MenuResponse
import team.comit.simtong.domain.menu.spi.QueryMenuPort
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
    private val queryMenuPort: QueryMenuPort
) {

    fun execute(today: LocalDate): MenuResponse {
        val year = today.year
        val month = today.monthValue

        val menu = queryMenuPort.queryMenuByMonth(year, month)
        val result = menu.map { MenuResponse.MenuElement(it.date, it.meal) }

        return MenuResponse(result)
    }

}