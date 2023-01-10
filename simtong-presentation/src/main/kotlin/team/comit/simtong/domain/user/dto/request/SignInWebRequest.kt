package team.comit.simtong.domain.user.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 *
 * 일반 사용자가 로그인을 요청하는 SignInWebRequest
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/09/08
 * @version 1.2.3
 **/
data class SignInWebRequest(

    @field:NotNull
    val employeeNumber: Int,

    @field:NotBlank
    val password: String,

    @field:NotBlank
    val deviceToken: String
)