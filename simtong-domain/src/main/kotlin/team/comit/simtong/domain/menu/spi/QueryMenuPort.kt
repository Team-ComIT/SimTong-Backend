package team.comit.simtong.domain.menu.spi

import team.comit.simtong.domain.menu.model.Menu
import java.util.*

/**
 *
 * Menu Domain에 관한 Query룰 요청하는 QueryMenuPort
 *
 * @author kimbeomjin
 * @date 2022/09/21
 * @version 1.0.0
 **/
interface QueryMenuPort {

    fun queryMenuBySpotId(year: Int, month: Int, spotId: UUID): List<Menu>

    fun queryMenuBySpotName(year: Int, month: Int, spotName: String): List<Menu>

}