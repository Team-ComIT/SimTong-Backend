package team.comit.simtong.global

/**
 *
 * 정규식들을 관리하는 RegexUtil
 *
 * @author kimbeomjin
 * @date 2022/10/16
 * @version 1.0.0
 **/
object RegexUtils {

    /**
     * first word - space X
     *
     * space , _ , . , a ~ z , A ~ Z , 가 ~ 힣 , 0 ~ 9
     *
     * length - 1 ~ 20
     **/
    const val NICKNAME_PATTERN = """(?=\S)(?=[a-zA-Z0-9가-힣._]{1,20}).*"""

    /**
     * non-space, $ , + , - , _ , a ~ z , A ~ Z , 0 ~ 9
     *
     * length - 8 ~ 20
     **/
    const val SECRET_PATTERN = """[a-zA-Z0-9_+\-$]{8,20}"""

}