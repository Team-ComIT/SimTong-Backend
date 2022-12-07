package team.comit.simtong.persistence.file

import org.springframework.stereotype.Component
import team.comit.simtong.domain.file.spi.EmployeeCertificatePort
import team.comit.simtong.persistence.file.mapper.EmployeeCertificateMapper

/**
 *
 * 직원 명부의 영속성을 관리하는 EmployeeProofPersistenceAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/12/06
 * @version 1.0.0
 **/
@Component
class EmployeeCertificatePersistenceAdapter(
    private val employeeCertificateJpaRepository: EmployeeCertificateJpaRepository,
    private val employeeCertificateMapper: EmployeeCertificateMapper
) : EmployeeCertificatePort {
    override fun existsEmployeeCertificateByNameAndEmployeeNumber(
        name: String,
        employeeNumber: Int
    ) = employeeCertificateJpaRepository.existsByNameAndEmployeeNumber(name, employeeNumber)

}