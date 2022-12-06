package team.comit.simtong.domain.file.model

/**
 *
 * Employee Proof Aggregate Root를 담당하는 EmployeeProof
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