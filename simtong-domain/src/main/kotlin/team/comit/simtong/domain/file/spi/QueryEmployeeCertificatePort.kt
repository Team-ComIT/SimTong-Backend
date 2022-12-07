package team.comit.simtong.domain.file.spi

/**
 *
 * EmployeeCertificate Domain에 관한 Query를 요청하는 QueryEmployeeCertificatePort
 *
 * @author kimbeomjin
 * @date 2022/12/07
 * @version 1.0.0
 **/
interface QueryEmployeeCertificatePort {

    fun existsEmployeeCertificateByNameAndEmployeeNumber(name: String, employeeNumber: Int): Boolean

}