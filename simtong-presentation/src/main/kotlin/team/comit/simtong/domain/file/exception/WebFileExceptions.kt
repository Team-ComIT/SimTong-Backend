package team.comit.simtong.domain.file.exception

import team.comit.simtong.global.exception.WebException

/**
 *
 * 표현 계층에서 발생하는 예외를 관리하는 WebFileExceptions
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/12/17
 * @version 1.2.3
 **/
sealed class WebFileExceptions(
    override val status: Int,
    override val message: String
) : WebException(status, message) {

    // 400
    class InvalidExtension(message: String = INVALID_EXTENSION) : WebFileExceptions(400, message)

    companion object {
        private const val INVALID_EXTENSION = "확장자가 유효하지 않습니다."
    }
}
