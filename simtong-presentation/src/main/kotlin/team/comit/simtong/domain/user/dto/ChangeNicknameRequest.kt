package team.comit.simtong.domain.user.dto

import team.comit.simtong.domain.user.dto.request.ChangeNicknameData
import team.comit.simtong.domain.user.value.NickName
import javax.validation.constraints.Pattern

/**
 *
 * 닉네임 변경을 요청하는 ChangeNicknameRequest
 *
 * @author Chokyunghyeon
 * @date 2022/10/03
 * @version 1.2.3
 **/
data class ChangeNicknameRequest(
    @field:Pattern(regexp = NickName.PATTERN)
    val nickname: String
) {

    fun toData() = ChangeNicknameData(
        nickname = nickname
    )
}