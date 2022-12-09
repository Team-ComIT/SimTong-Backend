package team.comit.simtong.global.error

/**
 *
 * 표현 계층의 ErrorCode 형식을 맞추기 위해 사용하는 WebErrorProperty
 *
 * @author Chokyunghyeon
 * @date 2022/12/09
 * @version 1.0.0
 **/
interface WebErrorProperty {

    fun status(): Int

    fun message(): String

}