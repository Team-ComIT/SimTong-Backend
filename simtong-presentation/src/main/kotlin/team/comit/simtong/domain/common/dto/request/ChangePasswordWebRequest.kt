package team.comit.simtong.domain.common.dto.request

import team.comit.simtong.domain.user.model.Password
import javax.validation.constraints.Pattern

/**
 *
 * 비밀번호 변경을 요청하는 ChangePasswordWebRequest
 *
 * @author Chokyunghyeon
 * @date 2022/10/14
 * @version 1.2.3
 **/
data class ChangePasswordWebRequest(
    @field:Pattern(regexp = Password.PATTERN)
    val password: Password,

    @field:Pattern(regexp = Password.PATTERN)
    val newPassword: Password
)