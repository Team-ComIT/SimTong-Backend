package team.comit.simtong.global.security.error

import org.springframework.http.HttpStatus
import team.comit.simtong.global.error.ErrorProperty

/**
 *
 * Security와 관련해 발생하는 ErrorCode를 관리하는 SecurityErrorCode
 *
 * @author kimbeomjin
 * @date 2022/08/31
 * @version 1.0.0
 **/
enum class SecurityErrorCode(
    private val status: HttpStatus,
    private val message: String
) : ErrorProperty {

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰");

    override fun status(): Int = status.value()
    override fun message(): String = message
}