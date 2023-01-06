package team.comit.simtong.domain.user.value

/**
 *
 * 비밀번호를 의미하는 Password
 *
 * @author Chokyunghyeon
 * @date 2023/01/04
 * @version 1.2.3
 **/
@JvmInline
value class Password(
    val value: String
) {
    companion object {
        const val MIN_LENGTH = 8
        const val MAX_LENGTH = 20

        /**
         * a ~ z or A ~ Z & 0 ~ 9 - must more than once
         *
         * non-space, $ , + , - , _ , a ~ z , A ~ Z , 0 ~ 9
         */
        const val PATTERN = """(?=.*[a-zA-Z])(?=.*\d)(?=^[\w$+-]*$).*"""
    }
}