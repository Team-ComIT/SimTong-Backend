package team.comit.simtong.persistence.user

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.UserPort
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
    private val userMapper: UserMapper
) : UserPort {

    override fun queryUserById(id: UUID) = userMapper.toDomain(
        userJpaRepository.findByIdOrNull(id)
    )

    override fun queryUserByEmployeeNumber(employeeNumber: Int) = userMapper.toDomain(
        userJpaRepository.queryUserJpaEntityByEmployeeNumber(employeeNumber)
    )

    override fun queryUserByEmail(email: String) = userMapper.toDomain(
        userJpaRepository.queryUserJpaEntityByEmail(email)
    )

    override fun queryUserByNickName(nickName: String) = userMapper.toDomain(
        userJpaRepository.queryUserJpaEntityByNickname(nickName)
    )

    override fun queryUserByNameAndSpotAndEmail(name: String, spotId: UUID, email: String) = userMapper.toDomain(
        userJpaRepository.queryUserJpaEntityByNameAndSpotIdAndEmail(name, spotId, email)
    )

    override fun queryUserByEmailAndEmployeeNumber(email: String, employeeNumber: Int) = userMapper.toDomain(
        userJpaRepository.queryUserJpaEntityByEmailAndEmployeeNumber(email, employeeNumber)
    )

    override fun existsUserByEmail(email: String) = userJpaRepository.existsUserJpaEntitiesByEmail(email)

    override fun existsUserByNickname(nickname: String) = userJpaRepository.existsUserJpaEntitiesByNickname(nickname)

    override fun save(user: User) = userMapper.toDomain(
        userJpaRepository.save(
            userMapper.toEntity(user)
        )
    )!!

}