package team.comit.simtong.domain.file.error

import team.comit.simtong.global.error.WebErrorProperty

/**
 *
 * 표현 계층의 File Error를 관리하는 WebFileErrorCode
 *
 * @author Chokyunghyeon
 * @date 2022/12/09
 * @version 1.0.0
 **/
enum class WebFileErrorCode(
    private val status: Int,
    private val message: String
) : WebErrorProperty {

    INVALID_EXTENSION(400, "제한된 확장자");

    override fun status(): Int = status

    override fun message(): String = message

}