package team.comit.simtong.domain.menu.exception

import team.comit.simtong.domain.menu.error.MenuErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * 같은 달에 메뉴가 이미 존재하는 경우 발생시키는 MenuAlreadyExistsSameMonthException
 *
 * @author kimbeomjin
 * @date 2022/12/11
 * @version 1.0.0
 **/
class MenuAlreadyExistsSameMonthException private constructor() :
    BusinessException(MenuErrorCode.ALREADY_EXISTS_SAME_MONTH) {

    companion object {
        @JvmField
        val EXCEPTION = MenuAlreadyExistsSameMonthException()
    }
}