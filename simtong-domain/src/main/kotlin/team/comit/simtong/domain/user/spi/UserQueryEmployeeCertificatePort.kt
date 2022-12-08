package team.comit.simtong.domain.user.spi

import team.comit.simtong.domain.file.model.EmployeeCertificate

/**
 *
 * User Domain에서 Employee Certificate에 관한 Query를 요청하는 UserQueryEmployeeCertificatePort
 *
 * @author Chokyunghyeon
 * @date 2022/12/07
 * @version 1.0.0
 **/
interface UserQueryEmployeeCertificatePort {

    fun queryEmployeeCertificateByNameAndEmployeeNumber(name: String, employeeNumber: Int) : EmployeeCertificate?
}