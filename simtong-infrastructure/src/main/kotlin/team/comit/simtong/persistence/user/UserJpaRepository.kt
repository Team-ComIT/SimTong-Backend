package team.comit.simtong.persistence.user

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.comit.simtong.persistence.user.entity.UserJpaEntity
import java.util.*

/**
 *
 * Spring Repository의 기능을 이용하는 UserJpaRepository
 *
 * @author kimbeomjin
 * @author JoKyungHyeon
 * @date 2022/08/21
 * @version 1.1.0
 **/
@Repository
interface UserJpaRepository : CrudRepository<UserJpaEntity, UUID> {

    fun queryUserJpaEntityById(id: UUID): UserJpaEntity?

    fun queryUserJpaEntityByEmail(email: String): UserJpaEntity?

    fun queryUserJpaEntityByNickname(nickName: String): UserJpaEntity?

    fun existsUserJpaEntitiesByEmail(email: String): Boolean


}