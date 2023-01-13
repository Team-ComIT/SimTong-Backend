package team.comit.simtong.domain.user.model

/**
 *
 * User Aggregate 중 비밀번호를 담당하는 Password
 *
 * @author kimbeomjin
 * @date 2023/01/10
 * @version 1.2.5
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
        const val PATTERN = """(?=.*[a-zA-Z])(?=.*\d)(?=^[\w$+-]{$MIN_LENGTH,$MAX_LENGTH$).*"""

        fun of(password: String) = Password(password)
    }
}