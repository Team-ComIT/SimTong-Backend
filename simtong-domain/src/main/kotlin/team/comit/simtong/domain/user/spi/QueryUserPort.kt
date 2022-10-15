package team.comit.simtong.domain.user.spi

import team.comit.simtong.domain.user.model.User
import java.util.UUID

/**
 *
 * User Domain에 관한 Query를 요청하는 QueryUserPort
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/04
 * @version 1.0.0
 **/
interface QueryUserPort {

    fun existsUserByEmail(email: String): Boolean

    fun existsUserByNickname(nickname: String): Boolean

    fun queryUserById(id: UUID): User?

    fun queryUserByEmployeeNumber(employeeNumber: Int): User?

    fun queryUserByEmail(email: String): User?

    fun queryUserByNickName(nickName: String): User?

    fun queryUserByNameAndSpotAndEmail(name: String, spotId: UUID, email: String): User?

    fun queryUserByEmailAndEmployeeNumber(email: String, employeeNumber: Int): User?

}