package team.comit.simtong.persistence.file.mapper

import org.mapstruct.Mapper
import team.comit.simtong.domain.file.model.EmployeeCertificate
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.file.entity.EmployeeCertificateJpaEntity

/**
 *
 * EmployeeCertificate Entity와 Employee Certificate Aggregate를 변환하는 EmployeeCertificateMapper
 *
 * @author Chokyunghyeon
 * @date 2022/12/06
 * @version 1.0.0
 **/
@Mapper
abstract class EmployeeCertificateMapper : GenericMapper<EmployeeCertificateJpaEntity, EmployeeCertificate> {
}