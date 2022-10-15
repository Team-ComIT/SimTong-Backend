package team.comit.simtong.common.dto.request

import java.util.UUID
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 *
 * 사원 번호 찾기를 요청하는 FindEmployeeNumberWebRequest
 *
 * @author Chokyunghyeon
 * @date 2022/09/11
 * @version 1.0.0
 **/
data class FindEmployeeNumberWebRequest(
    @field:NotBlank
    val name: String,

    @field:NotNull
    val spotId: UUID,

    @field:NotBlank
    @field:Email
    val email: String
)