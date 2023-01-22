package team.comit.simtong.domain.common.dto

import team.comit.simtong.domain.user.dto.request.ChangePasswordData
import team.comit.simtong.domain.user.model.Password
import javax.validation.constraints.Pattern

/**
 *
 * 비밀번호 변경을 요청하는 ChangePasswordRequest
 *
 * @author Chokyunghyeon
 * @date 2022/10/14
 * @version 1.2.5
 **/
data class ChangePasswordRequest(
    @field:Pattern(regexp = Password.PATTERN)
    val password: String,

    @field:Pattern(regexp = Password.PATTERN)
    val newPassword: String
) {

    fun toData() = ChangePasswordData(
        password = password,
        newPassword = newPassword
    )
}