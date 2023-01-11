package team.comit.simtong.domain.user.dto

import team.comit.simtong.domain.user.model.value.NickName
import javax.validation.constraints.Pattern

/**
 *
 * 닉네임 변경을 요청하는 ChangeNicknameWebRequest
 *
 * @author Chokyunghyeon
 * @date 2022/10/03
 * @version 1.2.5
 **/
data class ChangeNicknameWebRequest(
    @Pattern(regexp = NickName.PATTERN)
    val nickname: String
)