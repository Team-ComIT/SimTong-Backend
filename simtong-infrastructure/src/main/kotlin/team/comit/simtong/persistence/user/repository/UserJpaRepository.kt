package team.comit.simtong.persistence.user.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.comit.simtong.persistence.user.entity.UserJpaEntity
import java.util.UUID

/**
 *
 * Spring Repository의 기능을 이용하는 UserJpaRepository
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/08/21
 * @version 1.0.0
 **/
@Repository
interface UserJpaRepository : CrudRepository<UserJpaEntity, UUID> {

    fun queryUserJpaEntityByEmployeeNumber(employeeNumber: Int): UserJpaEntity?

    fun queryUserJpaEntityByEmail(email: String): UserJpaEntity?

    fun queryUserJpaEntityByNickname(nickName: String): UserJpaEntity?

    fun existsUserJpaEntitiesByEmail(email: String): Boolean

    fun existsUserJpaEntityByEmployeeNumberAndEmail(employeeNumber: Int, email: String): Boolean

    fun existsUserJpaEntitiesByNickname(nickname: String): Boolean

    fun existsUserJpaEntitiesByEmailOrEmployeeNumber(email: String, employeeNumber: Int): Boolean

    fun queryUserJpaEntityByNameAndSpotIdAndEmail(name: String, spotId: UUID, email: String): UserJpaEntity?

    fun queryUserJpaEntityByEmailAndEmployeeNumber(email: String, employeeNumber: Int): UserJpaEntity?

}