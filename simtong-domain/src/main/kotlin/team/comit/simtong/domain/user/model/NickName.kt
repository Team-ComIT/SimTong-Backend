package team.comit.simtong.domain.user.model

/**
 *
 * User Aggregate 중 닉네임을 담당하는 NickName
 *
 * @author kimbeomjin
 * @date 2023/01/10
 * @version 1.2.5
 **/
@JvmInline
value class NickName(
    val value: String
) {

    companion object {
        const val MIN_LENGTH = 1
        const val MAX_LENGTH = 20

        /**
         * first word & Last word - space X
         *
         * space , _ , . , a ~ z , A ~ Z , 가 ~ 힣 , 0 ~ 9
         */
        const val PATTERN = """(?=^\S)(?=^[\w\s가-힣.]{$MIN_LENGTH,$MAX_LENGTH}$).*(?=\S$)."""

        fun of(nickName: String) = NickName(nickName)
    }
}