package team.comit.simtong.domain.file.dto.request

import org.springframework.web.multipart.MultipartFile

/**
 *
 * 파일을 전달하는 WebFileRequest
 *
 * @author Chokyunghyeon
 * @date 2022/09/08
 * @version 1.0.0
 **/
data class WebFileRequest (
    val file: MultipartFile
)