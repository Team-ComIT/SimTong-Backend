package team.comit.simtong.global.value

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

        /**
         * a ~ z or A ~ Z & 0 ~ 9 - must more than once
         *
         * non-space, $ , + , - , _ , a ~ z , A ~ Z , 0 ~ 9
         *
         * length - 8 ~ 20
         **/
        const val PATTERN = """(?=.*[a-zA-Z])(?=.*\d)(?=^[\w$+-]{8,20}$).*"""
    }
}