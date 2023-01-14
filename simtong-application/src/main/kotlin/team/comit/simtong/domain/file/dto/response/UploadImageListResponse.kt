package team.comit.simtong.domain.file.dto.response

/**
 *
 * 다중 이미지 업로드 요청 결과를 전송하는 UploadImageListResponse
 *
 * @author Chokyunghyeon
 * @date 2022/09/21
 * @version 1.2.5
 **/
data class UploadImageListResponse(
    val filePathList: List<String>
)