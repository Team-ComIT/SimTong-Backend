package team.comit.simtong.global.error

import org.springframework.http.HttpStatus

/**
 *
 * 전체적으로 발생하는 ErrorCode를 관리하는 GlobalErrorCode
 *
 * @author kimbeomjin
 * @date 2022/08/22
 * @version 1.0.0
 **/
enum class GlobalErrorCode(
    private val status: HttpStatus,
    private val message: String
) : ErrorProperty {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 입력값"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 HTTP 메소드"),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러");

    override fun status(): Int = status.value()
    override fun message(): String = message
}