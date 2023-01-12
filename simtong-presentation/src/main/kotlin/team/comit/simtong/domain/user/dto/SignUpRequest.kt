package team.comit.simtong.domain.user.dto

import org.hibernate.validator.constraints.Range
import team.comit.simtong.domain.user.dto.request.SignUpData
import team.comit.simtong.domain.user.value.EmployeeNumber
import team.comit.simtong.domain.user.value.NickName
import team.comit.simtong.domain.user.value.Password
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

/**
 *
 * 회원 가입을 요청하는 SignUpRequest
 *
 * @author Chokyunghyeon
 * @date 2022/09/04
 * @version 1.2.3
 **/
data class SignUpRequest(

    @field:NotBlank
    val name: String,

    @field:NotBlank
    @field:Email
    val email: String,

    @field:Range(min = EmployeeNumber.MIN_VALUE, max = EmployeeNumber.MAX_VALUE)
    val employeeNumber: Int,

    @field:NotNull
    @field:Pattern(regexp = Password.PATTERN)
    val password: String,

    @field:NotNull
    @field:Pattern(regexp = NickName.PATTERN)
    val nickname: String,

    val profileImagePath: String?,

    @field:NotBlank
    val deviceToken: String
) {

    fun toData() = SignUpData(
        name = name,
        email = email,
        employeeNumber = employeeNumber,
        password = password,
        nickname = nickname,
        profileImagePath = profileImagePath,
        deviceToken = deviceToken
    )
}