package team.comit.simtong.domain.file.exception

import team.comit.simtong.global.exception.WebException

/**
 *
 * WebFileExceptions
 *
 * @author kimbeomjin
 * @date 2022/12/17
 * @version 1.0.0
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
