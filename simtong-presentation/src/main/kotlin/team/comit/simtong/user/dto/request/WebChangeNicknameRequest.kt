package team.comit.simtong.user.dto.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class WebChangeNicknameRequest(
    @field:NotBlank
    @field:Pattern(regexp = """^[가-힣][\s_.가-힣]*""")
    @field:Length(max = 20)
    val nickname: String
)