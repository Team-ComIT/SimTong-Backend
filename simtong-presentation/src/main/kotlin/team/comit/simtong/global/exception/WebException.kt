package team.comit.simtong.global.exception

/**
 *
 * 표현 계층에서 상황에 따라 예외를 처리하도록 Runtime Exception을 상속받은 WebException
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/12/09
 * @version 1.0.0
 **/
abstract class WebException(
    open val status: Int,
    override val message: String
) : RuntimeException()