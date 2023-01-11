package team.comit.simtong.domain.user.model

/**
 *
 * User Aggregate 중 사원번호를 담당하는 EmployeeNumber
 *
 * @author kimbeomjin
 * @date 2023/01/10
 * @version 1.2.5
 **/
@JvmInline
value class EmployeeNumber(
    val value: Int
) {

    companion object {
        const val MIN_VALUE: Long = 1200000000
        const val MAX_VALUE: Long = 1299999999

        fun of(employeeNumber: Int) = EmployeeNumber(employeeNumber)
    }
}