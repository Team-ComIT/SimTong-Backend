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


    ;

    override fun status(): Int = status

    override fun message(): String = message

}