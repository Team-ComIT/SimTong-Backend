package team.comit.simtong.global.error

/**
 *
 * 모든 도메인별 ErrorCode 형식을 맞추기 위해 사용하는 ErrorProperty
 *
 * @author kimbeomjin
 * @date 2022/08/22
 * @version 1.0.0
 **/
interface ErrorProperty {

    fun status(): Int

    fun message(): String

}