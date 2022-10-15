package team.comit.simtong

/**
 *
 * 정규식들을 관리하는 RegexUtil
 *
 * @author kimbeomjin
 * @date 2022/10/16
 * @version 1.0.0
 **/
object RegexUtil {

    const val NICKNAME_PATTERN = """^[가-힣][\s_.가-힣]*"""

    const val PASSWORD_PATTERN = """[+\-_$\w]*"""

}