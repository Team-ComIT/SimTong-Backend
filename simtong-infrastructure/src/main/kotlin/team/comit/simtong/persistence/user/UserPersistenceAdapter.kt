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

    override fun queryUserById(id: UUID) = userMapper.toDomain(
        userJpaRepository.queryUserJpaEntityById(id)
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

    override fun existsUserByEmail(email: String) = userJpaRepository.existsUserJpaEntitiesByEmail(email)


    override fun save(user: User) = userMapper.toDomain(
        userJpaRepository.save(
            userMapper.toEntity(user)
        ))!!

}