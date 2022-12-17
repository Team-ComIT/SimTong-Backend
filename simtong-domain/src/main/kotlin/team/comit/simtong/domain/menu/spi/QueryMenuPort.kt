package team.comit.simtong.domain.menu.spi

import team.comit.simtong.domain.menu.model.Menu
import java.time.LocalDate
import java.util.UUID

/**
 *
 * Menu Domain에 관한 Query룰 요청하는 QueryMenuPort
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/09/21
 * @version 1.0.0
 **/
interface QueryMenuPort {

    fun existsMenuByMonthAndSpotId(date: LocalDate, spotId: UUID): Boolean

    fun queryMenusByPeriodAndSpotId(startAt: LocalDate, endAt: LocalDate, spotId: UUID): List<Menu>

    fun queryMenusByPeriodAndSpotName(startAt: LocalDate, endAt: LocalDate, spotName: String): List<Menu>

}