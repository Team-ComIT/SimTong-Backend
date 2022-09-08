package team.comit.simtong.domain.file.dto.request

import org.springframework.web.multipart.MultipartFile
import javax.validation.constraints.NotNull

/**
 *
 * 여러 파일을 전달하는 WebMultiFileRequest
 *
 * @author Chokyunghyeon
 * @date 2022/09/08
 * @version 1.0.0
 **/
class WebMultiFileRequest (
    @field:NotNull
    val files: List<MultipartFile>

)