package team.comit.simtong.domain.menu.dto.request

import java.io.File

/**
 *
 * 메뉴 저장 요청 정보를 전달하는 SaveMenuData
 *
 * @author Chokyunghyeon
 * @date 2022/12/17
 * @version 1.2.5
 **/
data class SaveMenuData(
    val file: File,

    val year: Int,

    val month: Int
)