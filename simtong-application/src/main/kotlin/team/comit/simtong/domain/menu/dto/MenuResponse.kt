package team.comit.simtong.domain.menu.dto

import java.time.LocalDate

/**
 *
 * 메뉴를 반환하는 MenuResponse
 *
 * @author kimbeomjin
 * @date 2022/09/26
 * @version 1.0.0
 **/
data class MenuResponse(
    val menu: List<MenuElement>
) {
    data class MenuElement(
        val date: LocalDate,
        val meal: String
    )
}