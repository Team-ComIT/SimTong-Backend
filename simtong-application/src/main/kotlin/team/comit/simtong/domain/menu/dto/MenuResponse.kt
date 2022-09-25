package team.comit.simtong.domain.menu.dto

import java.time.LocalDate

data class MenuResponse(
    val menu: List<MenuElement>
) {
    data class MenuElement(
        val date: LocalDate,
        val meal: String
    )
}