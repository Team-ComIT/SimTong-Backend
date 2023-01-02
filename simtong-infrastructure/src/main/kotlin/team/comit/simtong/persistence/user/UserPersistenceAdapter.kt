package team.comit.simtong.persistence.user

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.comit.simtong.domain.user.model.DeviceToken
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.UserPort
import team.comit.simtong.persistence.user.mapper.DeviceTokenMapper
import team.comit.simtong.persistence.user.mapper.UserMapper
import team.comit.simtong.persistence.user.repository.DeviceTokenJpaRepository
import team.comit.simtong.persistence.user.repository.UserJpaRepository
import java.util.UUID

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
    private val userMapper: UserMapper,
    private val userJpaRepository: UserJpaRepository,
    private val deviceTokenMapper: DeviceTokenMapper,
    private val deviceTokenRepository: DeviceTokenJpaRepository
) : UserPort {

    override fun queryUserById(id: UUID): User? {
        return userJpaRepository.findByIdOrNull(id)
            .let(userMapper::toDomain)
    }

    override fun queryUserByEmployeeNumber(employeeNumber: Int): User? {
        return userJpaRepository.queryUserJpaEntityByEmployeeNumber(employeeNumber)
            .let(userMapper::toDomain)
    }

    override fun queryUserByEmail(email: String): User? {
        return userJpaRepository.queryUserJpaEntityByEmail(email)
            .let(userMapper::toDomain)
    }

    override fun queryUserByNickName(nickName: String): User? {
        return userJpaRepository.queryUserJpaEntityByNickname(nickName)
            .let(userMapper::toDomain)
    }

    override fun queryUserByNameAndSpotAndEmail(name: String, spotId: UUID, email: String): User? {
        return userJpaRepository.queryUserJpaEntityByNameAndSpotIdAndEmail(name, spotId, email)
            .let(userMapper::toDomain)
    }

    override fun queryUserByEmailAndEmployeeNumber(email: String, employeeNumber: Int): User? {
        return userJpaRepository.queryUserJpaEntityByEmailAndEmployeeNumber(email, employeeNumber)
            .let(userMapper::toDomain)
    }

    override fun existsUserByEmployeeNumber(employeeNumber: Int): Boolean {
        return userJpaRepository.existsUserJpaEntitiesByEmployeeNumber(employeeNumber)
    }

    override fun existsUserByEmail(email: String): Boolean {
        return userJpaRepository.existsUserJpaEntitiesByEmail(email)
    }

    override fun existsUserByNickname(nickname: String): Boolean {
        return userJpaRepository.existsUserJpaEntitiesByNickname(nickname)
    }

    override fun existsUserByEmployeeNumberAndEmail(employeeNumber: Int, email: String): Boolean {
        return userJpaRepository.existsUserJpaEntityByEmployeeNumberAndEmail(employeeNumber, email)
    }

    override fun save(user: User): User {
        return userJpaRepository.save(
            userMapper.toEntity(user)
        ).let { userMapper.toDomain(it)!! }
    }

    override fun save(deviceToken: DeviceToken): DeviceToken {
        return deviceTokenRepository.save(
            deviceTokenMapper.toEntity(deviceToken)
        ).let { deviceTokenMapper.toDomain(it)!! }
    }

    override fun queryDeviceTokenByUserId(userId: UUID): String? {
        return deviceTokenRepository.findByUserId(userId)?.token
    }

    override fun queryDeviceTokensByUserIds(userIds: List<UUID>): List<String> {
        return deviceTokenRepository.findAllByUserIdIsIn(userIds).map {
            it.token
        }
    }
}