package team.comit.simtong.domain.user.dto.request

import team.comit.simtong.global.RegexUtils
import javax.validation.constraints.Pattern

/**
 *
 * 닉네임 변경을 요청하는 ChangeNicknameWebRequest
 *
 * @author Chokyunghyeon
 * @date 2022/10/03
 * @version 1.0.0
 **/
data class ChangeNicknameWebRequest(
    @field:Pattern(regexp = RegexUtils.NICKNAME_PATTERN)
    val nickname: String
)