package team.comit.simtong.domain.common.dto.request

import java.util.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

/**
 *
 * 사원 번호 찾기를 요청하는 WebFindEmployeeNumberRequest
 *
 * @author Chokyunghyeon
 * @date 2022/09/11
 * @version 1.0.0
 **/
data class WebFindEmployeeNumberRequest(
    @field:NotBlank
    val name: String,

    @field:NotBlank
    val spotId: UUID,

    @field:NotBlank
    @field:Email
    val email: String
)