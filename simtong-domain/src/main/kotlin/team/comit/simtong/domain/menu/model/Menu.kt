package team.comit.simtong.domain.menu.model

import team.comit.simtong.global.annotation.Aggregate
import java.time.LocalDate
import java.util.UUID

/**
 *
 * MenuAggregate Root를 담당하는 Menu
 *
 * @author kimbeomjin
 * @date 2022/09/20
 * @version 1.0.0
 **/
@Aggregate
data class Menu(
    val date: LocalDate,

    val meal: String,

    val spotId: UUID
)