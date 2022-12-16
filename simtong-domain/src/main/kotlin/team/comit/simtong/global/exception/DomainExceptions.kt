package team.comit.simtong.global.exception

/**
 *
 * Domain에서 발생하는 Exception을 관리하는 DomainExceptions
 *
 * @author kimbeomjin
 * @date 2022/12/16
 * @version 1.0.0
 **/
sealed class DomainExceptions {

    // 500
    class NotInitializationProperties(message: String = NOT_INITIALIZATION_PROPERTIES) : BusinessException(500, message)

    companion object {
        private const val NOT_INITIALIZATION_PROPERTIES = "Domain Properties 초기화 실패"
    }
}