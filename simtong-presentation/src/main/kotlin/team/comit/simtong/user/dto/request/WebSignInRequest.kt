package team.comit.simtong.user.dto.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

/**
 *
 * 로그인을 요청하는 WebSignInRequest
 *
 * @author kimbeomjin
 * @date 2022/09/08
 * @version 1.0.0
 **/
data class WebSignInRequest(

    @field:NotNull
    @field:Min(1200000000)
    @field:Max(1299999999)
    val employeeNumber: Int,

    /**
     * $ , + , - , _ , a ~ z , A ~ Z , 0 ~ 9
     **/
    @field:NotBlank
    @field:Pattern(regexp = """[+\-_$\w]*""")
    @field:Length(max = 20)
    val password: String
)