package team.comit.simtong.domain.menu.usecase

import team.comit.simtong.domain.menu.model.Menu
import team.comit.simtong.domain.menu.spi.QueryMenuPort
import team.comit.simtong.domain.spot.model.Spot
import team.comit.simtong.global.annotation.ReadOnlyUseCase
import java.time.LocalDate

/**
 *
 * 공개된 Menu를 조회하는 QueryPublicMenuUseCase
 *
 * @author kimbeomjin
 * @date 2022/10/11
 * @version 1.2.5
 **/
@ReadOnlyUseCase
class QueryPublicMenuUseCase(
    private val queryMenuPort: QueryMenuPort,
) {

    fun execute(startAt: LocalDate, endAt: LocalDate): List<Menu> {
        return queryMenuPort.queryMenusByPeriodAndSpotName(startAt, endAt, Spot.HEAD_SHOP)
    }
}