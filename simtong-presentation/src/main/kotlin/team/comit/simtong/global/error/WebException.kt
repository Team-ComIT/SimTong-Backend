package team.comit.simtong.global.error

/**
 *
 * 표현 계층에서 상황에 따라 예외를 처리하도록 Runtime Exception을 상속받은 WebException
 *
 * @author Chokyunghyeon
 * @date 2022/12/09
 * @version 1.0.0
 **/
abstract class WebException(
    val exceptionProperty: WebErrorProperty
) : RuntimeException()