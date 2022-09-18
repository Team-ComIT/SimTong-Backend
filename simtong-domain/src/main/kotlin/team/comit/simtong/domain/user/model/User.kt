package team.comit.simtong.domain.user.model

import team.comit.simtong.global.annotation.Aggregate
import java.util.*

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

    val adminCode: String? = null,

    val profileImagePath: String
) {

    companion object {
        const val defaultImage = "" // TODO 기본 프로필 이미지 설정
    }

}