package team.comit.simtong.global

/**
 *
 * 정규식들을 관리하는 RegexUtil
 *
 * @author kimbeomjin
 * @date 2022/10/16
 * @version 1.0.0
 **/
object RegexUtil {

    /**
     * first word - 가 ~ 힣
     *
     * white space , _ , . , 가 ~ 힣
     **/
    const val NICKNAME_PATTERN = """^[가-힣][\s_.가-힣]*"""

    /**
     * $ , + , - , _ , a ~ z , A ~ Z , 0 ~ 9
     **/
    const val SECRET_PATTERN = """[+\-_$\w]*"""

}