package team.comit.simtong.domain.menu.dto

import org.springframework.web.multipart.MultipartFile
import team.comit.simtong.domain.file.converter.ExcelFileConverter
import team.comit.simtong.domain.menu.dto.request.SaveMenuData
import javax.validation.constraints.NotNull

/**
 *
 * 메뉴를 저장 요청하는 SaveMenuWebRequest
 *
 * @author Chokyunghyeon
 * @date 2022/12/17
 * @version 1.2.5
 **/
data class SaveMenuRequest(
    val file: MultipartFile,

    @field:NotNull
    val year: Int,

    @field:NotNull
    val month: Int
) {

    fun toData() = SaveMenuData(
        file = file.let(ExcelFileConverter::transferTo),
        year = year,
        month = month
    )
}