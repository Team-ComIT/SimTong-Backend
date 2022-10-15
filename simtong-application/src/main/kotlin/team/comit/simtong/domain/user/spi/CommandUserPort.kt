package team.comit.simtong.domain.user.spi

import team.comit.simtong.domain.user.model.User

/**
 *
 * User Domain의 저장을 요청하는 CommandUserPort
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/04
 * @version 1.0.0
 **/
interface CommandUserPort {

    fun save(user: User): User

}