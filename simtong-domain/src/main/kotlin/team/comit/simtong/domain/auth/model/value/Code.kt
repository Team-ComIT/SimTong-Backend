package team.comit.simtong.domain.auth.model.value

import net.bytebuddy.utility.RandomString

/**
 *
 * AuthCode Aggregate 중 인증코드를 담당하는 Code
 *
 * @author kimbeomjin
 * @date 2023/01/09
 * @version 1.2.5
 **/
@JvmInline
value class Code private constructor(
    val value: String
) {

    fun match(code: String): Boolean {
        return this.value == code
    }

    companion object {
        fun of(value: String) = Code(value)

        fun defaultValue() = Code(RandomString(6).nextString())
    }
}