package team.comit.simtong.common.dto.request

import org.hibernate.validator.constraints.Length
import team.comit.simtong.RegexUtil
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

/**
 *
 * 비밀번호 변경을 요청하는 WebChangePasswordRequest
 *
 * @author Chokyunghyeon
 * @date 2022/10/14
 * @version 1.0.0
 **/
data class WebChangePasswordRequest(

    @field:NotBlank
    val password: String,

    /**
     * $ , + , - , _ , a ~ z , A ~ Z , 0 ~ 9
     **/
    @field:NotBlank
    @field:Pattern(regexp = RegexUtil.PASSWORD_PATTERN)
    @field:Length(min = 8, max = 20)
    val newPassword: String
)