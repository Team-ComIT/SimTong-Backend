package team.comit.simtong.domain.user.spi

import team.comit.simtong.domain.user.model.User

/**
 *
 * User의 저장을 요청하는 SaveUserPort
 *
 * @author Chokyunghyeon
 * @date 2022/09/04
 * @version 1.0.0
 **/
interface SaveUserPort {

    fun saveUser(user: User): User

}