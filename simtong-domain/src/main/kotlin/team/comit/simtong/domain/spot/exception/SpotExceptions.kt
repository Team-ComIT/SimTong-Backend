package team.comit.simtong.domain.spot.exception

import team.comit.simtong.global.exception.BusinessException

/**
 *
 * Spot Domain에서 발생하는 Exception을 관리하는 SpotExceptions
 *
 * @author kimbeomjin
 * @date 2022/12/16
 * @version 1.0.0
 **/
sealed class SpotExceptions(
    override val status: Int,
    override val message: String
) : BusinessException(status, message) {

    // 404
    class NotFound(message: String = NOT_FOUND) : SpotExceptions(404, message)

    companion object {
        private const val NOT_FOUND = "지점을 찾을 수 없습니다."
    }
}
