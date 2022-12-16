package team.comit.simtong.domain.menu.exception

import team.comit.simtong.global.exception.BusinessException

/**
 *
 * Menu Domain에서 발생하는 Exception을 관리하는 MenuExceptions
 *
 * @author kimbeomjin
 * @date 2022/12/17
 * @version 1.0.0
 **/
sealed class MenuExceptions(
    override val status: Int,
    override val message: String
) : BusinessException(status, message) {

    // 409
    class AlreadyExistsSameMonth(message: String = ALREADY_EXISTS_SAME_MONTH) : MenuExceptions(409, message)

    companion object {
        private const val ALREADY_EXISTS_SAME_MONTH = "같은 달에 메뉴가 이미 존재합니다."
    }
}