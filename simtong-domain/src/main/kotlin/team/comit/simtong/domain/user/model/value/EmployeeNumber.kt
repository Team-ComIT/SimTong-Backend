package team.comit.simtong.domain.user.model.value

/**
 *
 * 사원번호를 의미하는 EmployeeNumber
 *
 * @author Chokyunghyeon
 * @date 2023/01/04
 * @version 1.2.3
 **/
@JvmInline
value class EmployeeNumber(
    val value: Int
) {
    companion object {
        const val MIN_VALUE: Long = 1200000000
        const val MAX_VALUE: Long = 1299999999
    }
}