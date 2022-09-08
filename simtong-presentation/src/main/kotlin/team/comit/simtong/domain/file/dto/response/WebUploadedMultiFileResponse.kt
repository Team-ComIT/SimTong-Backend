package team.comit.simtong.domain.file.dto.response

/**
 *
 * 업로드된 파일들을 전송하는 WebUploadedMultiFileResponse
 *
 * @author Chokyunghyeon
 * @date 2022/09/08
 * @version 1.0.0
 **/
data class WebUploadedMultiFileResponse (
    val filePathList: List<String>
)