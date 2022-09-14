package team.comit.simtong.persistence.user

import org.springframework.stereotype.Component
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.SaveUserPort
import team.comit.simtong.persistence.user.mapper.UserMapper
import team.comit.simtong.persistence.user.repository.UserJpaRepository
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
    private val userJpaProvider: UserJpaProvider,
    private val userMapper: UserMapper
): QueryUserPort, SaveUserPort {
    override fun queryUserById(id: UUID): User {
        return userMapper.toDomain(
            userJpaProvider.queryUserEntityById(id)
        )
    }

    override fun queryUserByEmployeeNumber(employeeNumber: Int): User {
        return userMapper.toDomain(
            userJpaProvider.queryUserEntityByEmployeeNumber(employeeNumber)
        )
    }

    override fun queryUserByEmail(email: String): User {
        return userMapper.toDomain(
            userJpaProvider.queryUserEntityByEmail(email)
        )
    }

    override fun queryUserByNickName(nickName: String): User {
        return userMapper.toDomain(
            userJpaProvider.queryUserEntityByNickName(nickName)
        )
    }

    override fun queryUserByNameAndSpotAndEmail(name: String, spot: String, email: String): User {
        return userMapper.toDomain(
            userJpaProvider.queryUserEntityByNameAndSpotAndEmail(name, spot, email)
        )
    }

    override fun existsUserByEmail(email: String): Boolean {
        return userJpaRepository.existsUserJpaEntitiesByEmail(email)
    }

    override fun saveUser(user: User): User {
        val entity = userJpaRepository.save(userMapper.toEntity(user))

        return userMapper.toDomain(entity)
    }

}