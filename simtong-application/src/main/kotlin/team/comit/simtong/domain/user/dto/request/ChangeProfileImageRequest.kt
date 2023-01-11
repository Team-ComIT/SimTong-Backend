package team.comit.simtong.domain.user.dto.request

/**
 *
 * 프로필 사진 변경 요청 정보를 전달하는 ChangeProfileImageRequest
 *
 * @author Chokyunghyeon
 * @date 2022/10/03
 * @version 1.0.0
 **/
data class ChangeProfileImageRequest(
    val profileImagePath: String
)