package team.comit.simtong.domain.user.spi

/**
 *
 * User의 Nickname에 관해 요청하는 NickNamePort
 *
 * @author Chokyunghyeon
 * @date 2022/09/05
 * @version 1.0.0
 **/
interface NickNamePort {

    fun random(): String

}