package team.comit.simtong.domain.user.model

/**
 *
 * 유저(직원, 관리자)의 권한을 관리하는 Authority
 *
 * @author kimbeomjin
 * @date 2022/08/21
 * @version 1.0.0
 **/
enum class Authority(
    val role: String
) {
    ROLE_COMMON("COMMON"),
    ROLE_ADMIN("ADMIN")
}