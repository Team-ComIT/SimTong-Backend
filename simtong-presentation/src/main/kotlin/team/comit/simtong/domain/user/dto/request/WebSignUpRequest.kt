package team.comit.simtong.domain.user.dto.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 *
 * 회원 가입을 요청하는 WebSignUpRequest
 *
 * @author Chokyunghyeon
 * @date 2022/09/04
 * @version 1.0.0
 **/
data class WebSignUpRequest (

    @field:NotBlank
    val name: String,

    @field:Email
    val email: String,

    @field:NotNull
    @field:Size(min = 1200000000, max = 1299999999)
    val employeeNumber: Int,

    /**
     * $ , + , - , _ , a ~ z , A ~ Z , 0 ~ 9
     **/
    @field:NotBlank
    @field:Pattern(regexp = """[+\-_$\w]*""")
    @field:Length(max = 20, min = 8)
    val password: String,

    /**
     * white space , _ , . , a ~ z, A ~ Z , 0 ~ 9
     **/
    @field:Pattern(regexp = """[\s_.\w]*""")
    @field:Length(max = 20)
    val nickname: String?,

    val profileImagePath: String?
)
