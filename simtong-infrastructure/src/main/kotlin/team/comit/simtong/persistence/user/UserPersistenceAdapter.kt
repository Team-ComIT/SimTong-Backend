package team.comit.simtong.persistence.user

import org.springframework.stereotype.Component
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.SaveUserPort
import team.comit.simtong.persistence.user.mapper.UserMapper
import java.util.*

/**
 *
 * User의 영속성을 관리하는 UserPersistenceAdapter
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/04
 * @version 1.0.0
 **/
@Component
class UserPersistenceAdapter(
    private val userJpaRepository: UserJpaRepository,
    private val userMapper: UserMapper
): QueryUserPort, SaveUserPort {
    override fun queryUserById(id: UUID): User? = userMapper.toDomain(
        userJpaRepository.queryUserJpaEntityById(id)
    )

    override fun queryUserByEmployeeNumber(employeeNumber: Int): User? = userMapper.toDomain(
        userJpaRepository.queryUserJpaEntityByEmployeeNumber(employeeNumber)
    )

    override fun queryUserByEmail(email: String): User? = userMapper.toDomain(
        userJpaRepository.queryUserJpaEntityByEmail(email)
    )

    override fun queryUserByNickName(nickName: String): User? = userMapper.toDomain(
        userJpaRepository.queryUserJpaEntityByNickname(nickName)
    )

    override fun existsUserByEmail(email: String): Boolean = userJpaRepository.existsUserJpaEntitiesByEmail(email)

    override fun save(user: User): User {
        val userEntity = userJpaRepository.save(userMapper.toEntity(user))

        return userMapper.toDomain(userEntity)!!
    }

}