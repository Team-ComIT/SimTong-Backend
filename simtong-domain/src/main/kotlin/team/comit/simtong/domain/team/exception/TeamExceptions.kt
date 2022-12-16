package team.comit.simtong.domain.team.exception

import team.comit.simtong.global.exception.BusinessException

/**
 *
 * Team Domain에서 발생하는 Exception을 관리하는 TeamExceptions
 *
 * @author kimbeomjin
 * @date 2022/12/16
 * @version 1.0.0
 **/
sealed class TeamExceptions private constructor(
    override val status: Int,
    override val message: String
) : BusinessException(status, message) {

    // 404
    class NotFound(message: String = NOT_FOUND) : TeamExceptions(404, message)

    companion object {
        private const val NOT_FOUND = "팀을 찾을 수 없습니다."
    }
}
