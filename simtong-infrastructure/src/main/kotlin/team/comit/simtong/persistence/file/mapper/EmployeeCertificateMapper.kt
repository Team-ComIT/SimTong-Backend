package team.comit.simtong.persistence.file.mapper

import org.mapstruct.Mapper
import team.comit.simtong.domain.file.model.EmployeeCertificate
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.file.entity.EmployeeCertificateJpaEntity

/**
 *
 * 직원 명부  EmployeeProofMapper
 *
 * @author Chokyunghyeon
 * @date 2022/12/06
 * @version 1.0.0
 **/
@Mapper
abstract class EmployeeCertificateMapper : GenericMapper<EmployeeCertificateJpaEntity, EmployeeCertificate> {
}