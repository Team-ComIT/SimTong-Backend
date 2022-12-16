package team.comit.simtong.domain.file.exception

import team.comit.simtong.global.exception.BusinessException

/**
 *
 * File Domain에서 발생하는 Exception을 관리하는 FileExceptions
 *
 * @author kimbeomjin
 * @date 2022/12/16
 * @version 1.0.0
 **/
sealed class FileExceptions(
    override val status: Int,
    override val message: String
) : BusinessException(status, message) {

    // 400
    class NotValidContent(message: String = NOT_VALID_CONTENT) : FileExceptions(400, message)

    // 401
    class NotExistsEmployee(message: String = NOT_EXISTS_EMPLOYEE) : FileExceptions(401, message)

    // 404
    class PathNotFound(message: String = PATH_NOT_FOUND) : FileExceptions(404, message)

    // 500
    class IOInterrupted(message: String = IO_INTERRUPTED) : FileExceptions(500, message)

    companion object {
        private const val NOT_VALID_CONTENT = "파일의 내용이 올바르지 않습니다."
        private const val NOT_EXISTS_EMPLOYEE = "일치하는 사원 정보가 존재하지 않습니다."
        private const val PATH_NOT_FOUND = "휴무일을 찾을 수 없습니다."
        private const val IO_INTERRUPTED = "파일 입출력 처리가 중단되었습니다."
    }
}
