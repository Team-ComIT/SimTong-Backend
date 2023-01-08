package team.comit.simtong.global.exception

/**
 *
 * 애플리케이션에서 발생하는 예외를 관리하는 GlobalExceptions
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/12/17
 * @version 1.2.3
 **/
sealed class GlobalExceptions(
    override val status: Int,
    override val message: String
) : BusinessException(status, message) {

    // 400
    class BadRequest(message: String = BAD_REQUEST) : GlobalExceptions(400, message)

    // 405
    class MethodNotAllowed(message: String = METHOD_NOT_ALLOWED) : GlobalExceptions(405, message)

    // 500
    class InternalServerError(message: String = INTERNAL_SERVER_ERROR) : GlobalExceptions(500, message)

    companion object {
        private const val BAD_REQUEST = "잘못된 입력값입니다."
        private const val METHOD_NOT_ALLOWED = "허용되지 않은 HTTP 메소드입니다."
        private const val INTERNAL_SERVER_ERROR = "서버 에러가 발생하였습니다."
    }
}
