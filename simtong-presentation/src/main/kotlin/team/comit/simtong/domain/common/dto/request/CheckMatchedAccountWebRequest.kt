package team.comit.simtong.domain.common.dto.request

import team.comit.simtong.global.value.EmployeeNumber
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
    val employeeNumber: EmployeeNumber,

    @field:NotBlank
    @field:Email
    val email: String
)