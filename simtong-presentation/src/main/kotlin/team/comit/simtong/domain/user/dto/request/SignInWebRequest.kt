package team.comit.simtong.domain.user.dto.request

import team.comit.simtong.global.value.EmployeeNumber
import team.comit.simtong.global.value.Password
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 *
 * 일반 사용자가 로그인을 요청하는 SignInWebRequest
 *
 * @author kimbeomjin
 * @date 2022/09/08
 * @version 1.2.3
 **/
data class SignInWebRequest(

    @field:NotNull
    val employeeNumber: EmployeeNumber,

    @field:NotBlank
    val password: Password,

    @field:NotBlank
    val deviceToken: String
)