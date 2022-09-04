package team.comit.simtong.domain.user.model

import team.comit.simtong.domain.user.spi.SecurityPort
import team.comit.simtong.global.annotation.Aggregate
import java.time.LocalDateTime
import java.util.UUID

/**
 *
 * User Aggregate를 담당하는 User
 *
 * @author Chokyunghyeon
 * @date 2022/09/04
 * @version 1.0.0
 **/
@Aggregate
data class User(
    val id: UUID?,

    val nickname: String,

    val name: String,

    val email: String,

    val password: String,

    val employeeNumber: Int,

    val authority: Authority,

    val adminCode: String?,

    val profileImagePath: String
) {

    companion object {
        private const val defaultImage = "" // TODO 기본 프로필 이미지 설정

        fun signUp(name: String, email: String, password: String,
                   nickname: String, employeeNumber: Int, profileImagePath: String?): User {

            return User(
                id = null,
                name = name,
                email = email,
                password = SecurityPort::encode.run { password },
                nickname = nickname,
                employeeNumber = employeeNumber,
                authority = Authority.ROLE_COMMON,
                adminCode = null,
                profileImagePath = profileImagePath ?: defaultImage
            )
        }
    }

}