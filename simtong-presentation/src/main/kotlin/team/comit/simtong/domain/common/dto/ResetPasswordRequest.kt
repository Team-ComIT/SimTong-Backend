package team.comit.simtong.domain.common.dto

import org.hibernate.validator.constraints.Range
import team.comit.simtong.domain.user.dto.request.ResetPasswordData
import team.comit.simtong.domain.user.model.EmployeeNumber
import team.comit.simtong.domain.user.model.Password
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

/**
 *
 * 비밀번호 초기화를 요청하는 ResetPasswordRequest
 *
 * @author Chokyunghyeon
 * @date 2022/10/03
 * @version 1.2.5
 **/
data class ResetPasswordRequest(
    @field:NotBlank
    @field:Email
    val email: String,

    @field:Range(min = EmployeeNumber.MIN_VALUE, max = EmployeeNumber.MAX_VALUE)
    val employeeNumber: Int,

    @field:Pattern(regexp = Password.PATTERN)
    val newPassword: String
) {

    fun toData() = ResetPasswordData(
        email = email,
        employeeNumber = employeeNumber,
        newPassword = newPassword
    )
}