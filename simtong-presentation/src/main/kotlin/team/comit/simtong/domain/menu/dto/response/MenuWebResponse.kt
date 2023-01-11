package team.comit.simtong.domain.menu.dto.response

import java.time.LocalDate

/**
 *
 * 메뉴를 반환하는 MenuWebResponse
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/09/26
 * @version 1.2.5
 **/
data class MenuWebResponse(
    val menu: List<MenuElement>
) {
    data class MenuElement(
        val date: LocalDate,
        val meal: String
    )
}