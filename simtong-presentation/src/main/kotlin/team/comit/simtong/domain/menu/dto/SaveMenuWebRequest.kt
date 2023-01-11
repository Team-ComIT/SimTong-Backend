package team.comit.simtong.domain.menu.dto

import org.springframework.web.multipart.MultipartFile
import javax.validation.constraints.NotNull

/**
 *
 * 메뉴를 저장 요청하는 SaveMenuWebRequest
 *
 * @author Chokyunghyeon
 * @date 2022/12/17
 * @version 1.0.0
 **/
data class SaveMenuWebRequest(
    val file: MultipartFile,

    @field:NotNull
    val year: Int,

    @field:NotNull
    val month: Int
)