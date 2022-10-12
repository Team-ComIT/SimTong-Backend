package team.comit.simtong.domain.menu.usecase

import team.comit.simtong.domain.menu.dto.MenuResponse
import team.comit.simtong.domain.menu.spi.QueryMenuPort
import team.comit.simtong.global.annotation.ReadOnlyUseCase
import java.time.LocalDate

/**
 *
 * 공개된 Menu를 조회하는 QueryPublicMenuUseCase
 *
 * @author kimbeomjin
 * @date 2022/10/11
 * @version 1.0.0
 **/
@ReadOnlyUseCase
class QueryPublicMenuUseCase(
    private val queryMenuPort: QueryMenuPort,
) {

    fun execute(today: LocalDate): MenuResponse {
        val menu = queryMenuPort.queryMenuBySpotName(today.year, today.monthValue, HEAD_SHOP)
        val result = menu.map { MenuResponse.MenuElement(it.date, it.meal) }

        return MenuResponse(result)
    }

    companion object {
        private const val HEAD_SHOP = "은행동 본점"
    }

}