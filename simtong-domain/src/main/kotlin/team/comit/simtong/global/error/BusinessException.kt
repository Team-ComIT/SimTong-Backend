package team.comit.simtong.global.error

/**
 *
 * 원하는 상황에 예외를 발생시켜 알맞게 처리하기 위해 RuntimeException을 상속받은 BusinessException
 *
 * @author kimbeomjin
 * @date 2022/08/22
 * @version 1.0.0
 **/
abstract class BusinessException(
    val exceptionProperty: ErrorProperty
) : RuntimeException()