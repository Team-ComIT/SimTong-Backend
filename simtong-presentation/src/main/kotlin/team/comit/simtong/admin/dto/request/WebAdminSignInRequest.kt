package team.comit.simtong.admin.dto.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 *
 * Admin 계정에 로그인 요청하는 WebAdminSignInRequest
 *
 * @author Chokyunghyeon
 * @date 2022/10/04
 * @version 1.0.0
 **/
data class WebAdminSignInRequest(
    @field:NotNull
    @field:Min(1200000000)
    @field:Max(1299999999)
    val employeeNumber: Int,

    @field:NotBlank
    @field:Length(min = 6, max = 6)
    val password: String
)