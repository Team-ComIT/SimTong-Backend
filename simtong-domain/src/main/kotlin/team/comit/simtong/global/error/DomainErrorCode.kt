package team.comit.simtong.global.error

/**
 *
 * Domain에서 발생하는 ErrorCode를 관리하는 DomainErrorCode
 *
 * @author Chokyunghyeon
 * @date 2022/11/26
 * @version 1.0.0
 **/
enum class DomainErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {

    NOT_INITIALIZATION_PROPERTIES(500, "Domain Properties 초기화 실패");

    override fun message() = message

    override fun status() = status
}