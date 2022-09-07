package team.comit.simtong.domain.user.model

import team.comit.simtong.global.annotation.Aggregate
import java.util.UUID

/**
 *
 * Root Aggregate를 담당하는 User
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
        const val defaultImage = "" // TODO 기본 프로필 이미지 설정
    }

}