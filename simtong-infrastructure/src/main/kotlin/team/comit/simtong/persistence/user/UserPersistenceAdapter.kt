package team.comit.simtong.persistence.user

import org.springframework.stereotype.Component
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.SaveUserPort
import team.comit.simtong.domain.user.exception.UserNotFoundException
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.persistence.user.entity.UserJpaEntity
import team.comit.simtong.persistence.user.mapper.UserMapper
import java.util.*

/**
 *
 * User의 영속성을 관리하는 UserPersistenceAdapter
 *
 * @author JoKyungHyeon
 * @date 2022/09/04
 * @version 1.0.0
 **/
@Component
class UserPersistenceAdapter(
    private val userJpaRepository: UserJpaRepository,
    private val userMapper: UserMapper
): QueryUserPort, SaveUserPort {
    override fun queryUserById(id: UUID): User {
        return userMapper.toDomain(queryUserEntityById(id))
    }

    override fun queryUserByEmail(email: String): User {
        return userMapper.toDomain(queryUserEntityByEmail(email))
    }

    override fun queryUserByNickName(nickName: String): User {
        return userMapper.toDomain(queryUserEntityByNickName(nickName))
    }

    override fun existsUserByEmail(email: String): Boolean {
        return userJpaRepository.existsUserJpaEntitiesByEmail(email)
    }

    override fun saveUser(user: User): User {
        val entity = userJpaRepository.save(userMapper.toEntity(user))

        return userMapper.toDomain(entity)
    }

    fun queryUserEntityById(id: UUID): UserJpaEntity {
        return userJpaRepository.queryUserJpaEntityById(id) ?: throw UserNotFoundException.EXCEPTION
    }

    fun queryUserEntityByEmail(email: String): UserJpaEntity {
        return userJpaRepository.queryUserJpaEntityByEmail(email) ?: throw UserNotFoundException.EXCEPTION
    }

    fun queryUserEntityByNickName(nickName: String): UserJpaEntity {
        return userJpaRepository.queryUserJpaEntityByNickname(nickName) ?: throw UserNotFoundException.EXCEPTION
    }

}