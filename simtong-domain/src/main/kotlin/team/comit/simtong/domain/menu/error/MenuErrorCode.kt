package team.comit.simtong.domain.menu.error

import team.comit.simtong.global.error.ErrorProperty

/**
 *
 * Menu에 대해 발생하는 Error를 관리하는 MenuErrorCode
 *
 * @author kimbeomjin
 * @date 2022/09/20
 * @version 1.0.0
 **/
enum class MenuErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {

    ALREADY_EXISTS_SAME_MONTH(400, "같은 달에 메뉴가 이미 존재합니다.")
    ;

    override fun status(): Int = status

    override fun message(): String = message

}