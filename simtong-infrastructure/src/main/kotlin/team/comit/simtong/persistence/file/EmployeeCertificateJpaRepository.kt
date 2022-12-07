package team.comit.simtong.persistence.file

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.comit.simtong.persistence.file.entity.EmployeeCertificateJpaEntity

/**
 *
 * Spring Repository 기능을 이용하는 EmployeeProofJpaRepository
 *
 * @author Chokyunghyeon
 * @date 2022/12/06
 * @version 1.0.0
 **/
@Repository
interface EmployeeCertificateJpaRepository : CrudRepository<EmployeeCertificateJpaEntity, Int> {

    fun existsByNameAndEmployeeNumber(name: String, employeeNumber: Int): Boolean

}