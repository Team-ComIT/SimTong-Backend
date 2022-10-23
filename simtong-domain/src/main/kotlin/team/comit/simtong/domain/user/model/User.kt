package team.comit.simtong.domain.user.model

import team.comit.simtong.global.annotation.Aggregate
import java.lang.System.getenv
import java.util.UUID

/**
 *
 * UserAggregate Root를 담당하는 User
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/04
 * @version 1.0.0
 **/
@Aggregate
data class User(
    val id: UUID = UUID(0, 0),

    val nickname: String,

    val name: String,

    val email: String,

    val password: String,

    val employeeNumber: Int,

    val authority: Authority,

    val spotId: UUID,

    val teamId: UUID,

    val profileImagePath: String
) {

    companion object {
        @JvmField
        val defaultImage: String = getenv("USER_DEFAULT_IMAGE")
    }

}