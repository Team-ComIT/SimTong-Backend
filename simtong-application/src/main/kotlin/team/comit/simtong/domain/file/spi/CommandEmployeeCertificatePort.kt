package team.comit.simtong.domain.file.spi

import team.comit.simtong.domain.file.model.EmployeeCertificate

/**
 *
 * Employee Certificate Domain에 관한 명령을 요청하는 CommandEmployeeCertificatePort
 *
 * @author Chokyunghyeon
 * @date 2022/12/06
 * @version 1.0.0
 **/
interface CommandEmployeeCertificatePort {

    fun saveAll(employeeCertificates: List<EmployeeCertificate>)
}