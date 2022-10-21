package team.comit.simtong.common.dto.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 *
 * 해당 사원번호와 이메일을 가지는 계정 여부 확인을 요청하는 CheckMatchedAccountWebRequest
 *
 * @author Chokyunghyeon
 * @date 2022/10/15
 * @version 1.0.0
 **/
data class CheckMatchedAccountWebRequest(
    @field:NotNull
    val employeeNumber: Int,

    @field:NotBlank
    @field:Email
    val email: String
)