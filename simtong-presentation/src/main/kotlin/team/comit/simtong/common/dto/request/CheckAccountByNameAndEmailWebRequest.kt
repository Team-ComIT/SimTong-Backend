package team.comit.simtong.common.dto.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

/**
 *
 * 해당 이름과 이메일을 가지는 계정 여부 확인을 요청하는 CheckAccountByNameAndEmailWebRequest
 *
 * @author Chokyunghyeon
 * @date 2022/10/15
 * @version 1.0.0
 **/
data class CheckAccountByNameAndEmailWebRequest(
    @field:NotBlank
    val name: String,

    @field:NotBlank
    @field:Email
    val email: String
)