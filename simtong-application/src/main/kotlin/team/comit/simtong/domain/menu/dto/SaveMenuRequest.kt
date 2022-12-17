package team.comit.simtong.domain.menu.dto

import java.io.File
import java.util.UUID

/**
 *
 * 메뉴 저장 요청 정보를 전달하는 SaveMenuRequest
 *
 * @author Chokyunghyeon
 * @date 2022/12/17
 * @version 1.0.0
 **/
data class SaveMenuRequest(
    val file: File,

    val year: Int,

    val month: Int,

    val spotId: UUID
)