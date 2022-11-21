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

    /**
     * 일반 계정
     */
    ROLE_COMMON("COMMON"),

    /**
     * 관리자 계정
     */
    ROLE_ADMIN("ADMIN"),

    /**
     * 최고 관리자 계정
     */
    ROLE_SUPER("SUPER")
}