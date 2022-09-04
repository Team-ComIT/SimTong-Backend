package team.comit.simtong.domain.user.dto.request

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.Range
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

/**
 *
 * 회원 가입을 요청하는 WebSignUpRequest
 *
 * @author JoKyungHyeon
 * @date 2022/09/04
 * @version 1.0.0
 **/
data class WebSignUpRequest (

    @field:NotNull
    val name: String,

    @field:Email
    val email: String,

    @field:NotNull
    @field:Range(max = 10, min = 10)
    val employee_number: Int,

    @field:NotNull
    @field:Pattern(regexp = """[+\-_$\w]*""")
    @field:Length(max = 20, min = 8)
    val password: String,

    @field:Pattern(regexp = """[\s_.\w]*""")
    @field:Length(max = 20)
    val nickname: String?,

    val profile_image_path: String?
)
