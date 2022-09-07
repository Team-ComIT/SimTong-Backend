package team.comit.simtong.domain.user.spi

import team.comit.simtong.domain.user.model.User
import java.util.UUID

/**
 *
 * User에 관한 Query를 요청하는 QueryUserPort
 *
 * @author Chokyunghyeon
 * @date 2022/09/04
 * @version 1.0.0
 **/
interface QueryUserPort {

    fun queryUserById(id: UUID): User

    fun queryUserByEmail(email: String): User

    fun queryUserByNickName(nickName: String): User

    fun existsUserByEmail(email: String): Boolean

}