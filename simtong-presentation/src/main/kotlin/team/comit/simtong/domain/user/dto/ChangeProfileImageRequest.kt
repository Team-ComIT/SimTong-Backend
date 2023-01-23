package team.comit.simtong.domain.user.dto

import team.comit.simtong.domain.user.dto.request.ChangeProfileImageData
import javax.validation.constraints.NotBlank

/**
 *
 * 프로필 사진 변경을 요청하는 ChangeProfileImageRequest
 *
 * @author Chokyunghyeon
 * @date 2022/10/03
 * @version 1.0.0
 **/
data class ChangeProfileImageRequest(

    @field:NotBlank
    val profileImagePath: String?
) {

    fun toData() = ChangeProfileImageData(
        profileImagePath = profileImagePath!!
    )
}