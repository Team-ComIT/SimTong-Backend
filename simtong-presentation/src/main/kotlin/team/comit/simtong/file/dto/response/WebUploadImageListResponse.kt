package team.comit.simtong.file.dto.response

/**
 *
 * 다중 이미지 업로드 요청 결과를 전송하는 WebUploadImageListResponse
 *
 * @author Chokyunghyeon
 * @date 2022/09/21
 * @version 1.0.0
 **/
data class WebUploadImageListResponse(
    val filePathList: List<String>
)