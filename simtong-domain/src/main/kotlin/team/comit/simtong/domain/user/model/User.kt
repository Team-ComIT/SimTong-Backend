package team.comit.simtong.domain.user.model

import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.global.DomainProperties.getProperty
import team.comit.simtong.global.DomainPropertiesPrefix
import team.comit.simtong.global.annotation.Aggregate
import java.time.LocalDateTime
import java.util.UUID

/**
 *
 * UserAggregate Root를 담당하는 User
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/04
 * @version 1.2.5
 **/
@Aggregate
data class User(
    val id: UUID,

    val nickname: NickName,

    val name: String,

    val email: String,

    val password: Password,

    val employeeNumber: EmployeeNumber,

    val authority: Authority,

    val spotId: UUID,

    val teamId: UUID,

    val profileImagePath: String,

    val deletedAt: LocalDateTime?
) {

    companion object {
        fun of(
            id: UUID = UUID(0, 0),
            nickname: String,
            name: String,
            email: String,
            password: String,
            employeeNumber: Int,
            authority: Authority,
            spotId: UUID,
            teamId: UUID,
            profileImagePath: String,
            deletedAt: LocalDateTime? = null
        ) = User(
            id = id,
            nickname = NickName.of(nickname),
            name = name,
            email = email,
            password = Password.of(password),
            employeeNumber = EmployeeNumber.of(employeeNumber),
            authority = authority,
            spotId = spotId,
            teamId = teamId,
            profileImagePath = profileImagePath,
            deletedAt = deletedAt
        )

        @JvmField
        val DEFAULT_IMAGE = getProperty(DomainPropertiesPrefix.USER_DEFAULT_IMAGE)
    }

    fun checkAuthority(authority: Authority) {
        when(authority) {
            Authority.ROLE_COMMON -> if (!isEmployee()) throw UserExceptions.DifferentPermissionAccount("사원 계정이 아닙니다.")

            Authority.ROLE_SUPER, Authority.ROLE_ADMIN -> if (!isAdmin()) throw UserExceptions.DifferentPermissionAccount("관리자 계정이 아닙니다.")
        }
    }

    fun isAdmin() = (this.authority == Authority.ROLE_ADMIN) || (this.authority == Authority.ROLE_SUPER)

    fun isEmployee() = this.authority == Authority.ROLE_COMMON

    fun changeEmail(email: String) = this.copy(email = email)

    fun changeNickname(nickname: String) = this.copy(nickname = NickName.of(nickname))

    fun changePassword(password: String) = this.copy(password = Password.of(password))

    fun changeProfileImage(profileImagePath: String) = this.copy(profileImagePath = profileImagePath)

    fun changeSpot(spotId: UUID) = this.copy(spotId = spotId)
}