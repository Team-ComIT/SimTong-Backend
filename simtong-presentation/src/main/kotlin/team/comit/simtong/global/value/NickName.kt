package team.comit.simtong.global.value

/**
 *
 * 닉네임을 의미하는 NickName
 *
 * @author Chokyunghyeon
 * @date 2023/01/04
 * @version 1.2.3
 **/
@JvmInline
value class NickName(
    val value: String
) {
    companion object {

        /**
         * first word & Last word - space X
         *
         * space , _ , . , a ~ z , A ~ Z , 가 ~ 힣 , 0 ~ 9
         *
         * length - 1 ~ 20
         **/
        const val PATTERN = """(?=^\S)(?=^[\w\s가-힣.]{1,20}$).*(?=\S$)."""
    }
}