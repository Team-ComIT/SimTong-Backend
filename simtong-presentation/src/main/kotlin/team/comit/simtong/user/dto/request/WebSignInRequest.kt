package team.comit.simtong.user.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 *
 * 로그인을 요청하는 WebSignInRequest
 *
 * @author kimbeomjin
 * @date 2022/09/08
 * @version 1.0.0
 **/
data class WebSignInRequest(

    @field:NotNull
    val employeeNumber: Int,

    @field:NotBlank
    val password: String
)