package team.comit.simtong.persistence.user

import org.springframework.stereotype.Component
import team.comit.simtong.domain.user.exception.UserNotFoundException
import team.comit.simtong.persistence.user.entity.UserJpaEntity
import team.comit.simtong.persistence.user.repository.UserJpaRepository
import java.util.*

/**
 *
 * JPA를 통해 DB로부터 정보를 조회하고 가공하는 UserJpaProvider
 *
 * @author kimbeomjin
 * @author ChoKyungHyeon
 * @date 2022/09/08
 * @version 1.0.0
 **/
@Component
class UserJpaProvider(
    private val userJpaRepository: UserJpaRepository
) {

    fun queryUserEntityById(id: UUID): UserJpaEntity {
        return userJpaRepository.queryUserJpaEntityById(id) ?: throw UserNotFoundException.EXCEPTION
    }

    fun queryUserEntityByEmail(email: String): UserJpaEntity {
        return userJpaRepository.queryUserJpaEntityByEmail(email) ?: throw UserNotFoundException.EXCEPTION
    }

    fun queryUserEntityByNickName(nickName: String): UserJpaEntity {
        return userJpaRepository.queryUserJpaEntityByNickname(nickName) ?: throw UserNotFoundException.EXCEPTION
    }

    fun queryUserEntityByEmployeeNumber(employeeNumber: Int): UserJpaEntity {
        return userJpaRepository.queryUserJpaEntityByEmployeeNumber(employeeNumber)
            ?: throw UserNotFoundException.EXCEPTION
    }

    fun queryUserEntityByNameAndSpotAndEmail(name: String, spot: String, email: String): UserJpaEntity {
        return userJpaRepository.queryUserJpaEntityByNameAndSpotAndEmail(name, spot, email) ?: throw UserNotFoundException.EXCEPTION
    }

}