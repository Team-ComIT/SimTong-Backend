package team.comit.simtong.user.dto.request

import org.hibernate.validator.constraints.Length
import team.comit.simtong.RegexUtil
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

/**
 *
 * 닉네임 변경을 요청하는 WebChangeNicknameRequest
 *
 * @author Chokyunghyeon
 * @date 2022/10/03
 * @version 1.0.0
 **/
data class WebChangeNicknameRequest(
    @field:NotBlank
    @field:Pattern(regexp = RegexUtil.NICKNAME_PATTERN)
    @field:Length(max = 20)
    val nickname: String
)