package team.comit.simtong.user.dto.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.*

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

    @field:NotBlank
    @field:Email
    val email: String,

    @field:NotNull
    @field:Min(1200000000)
    @field:Max(1299999999)
    val employeeNumber: Int,

    /**
     * $ , + , - , _ , a ~ z , A ~ Z , 0 ~ 9
     **/
    @field:NotBlank
    @field:Pattern(regexp = """[+\-_$\w]*""")
    @field:Length(max = 20, min = 8)
    val password: String,

    /**
     * first word - 가 ~ 힣
     *
     * white space , _ , . , 가 ~ 힣
     **/
    @field:Pattern(regexp = """^[가-힣][\s_.가-힣]*""")
    @field:Length(max = 20)
    val nickname: String?,

    val profileImagePath: String?
)
