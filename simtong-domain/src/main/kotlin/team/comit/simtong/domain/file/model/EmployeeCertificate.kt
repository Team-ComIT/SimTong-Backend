package team.comit.simtong.domain.file.model

/**
 *
 * EmployeeCertificate Aggregate Root를 담당하는 EmployeeCertificate
 * 직원을 증명하기 위해 사용되는 모델
 *
 * @author Chokyunghyeon
 * @date 2022/12/06
 * @version 1.0.0
 **/
data class EmployeeCertificate(
    val employeeNumber: Int,

    val name: String,

    val spotName: String,

    val teamName: String
)