package team.comit.simtong.domain.common.dto.request

import org.hibernate.validator.constraints.Length
import team.comit.simtong.domain.user.value.Password
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
    @field:Length(min = Password.MIN_LENGTH, max = Password.MAX_LENGTH)
    @field:Pattern(regexp = Password.PATTERN)
    val password: Password,

    @field:Length(min = Password.MIN_LENGTH, max = Password.MAX_LENGTH)
    @field:Pattern(regexp = Password.PATTERN)
    val newPassword: Password
)