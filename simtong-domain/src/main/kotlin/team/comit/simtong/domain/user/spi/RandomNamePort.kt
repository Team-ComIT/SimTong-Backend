package team.comit.simtong.domain.user.spi

/**
 *
 * User의 랜덤한 Nickname을 요청하는 RandomNamePort
 *
 * @author Chokyunghyeon
 * @date 2022/09/05
 * @version 1.0.0
 **/
interface RandomNamePort {

    fun randomNickname(): String

}