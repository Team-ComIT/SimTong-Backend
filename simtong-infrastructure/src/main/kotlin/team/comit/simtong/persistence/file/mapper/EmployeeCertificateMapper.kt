package team.comit.simtong.persistence.file.mapper

import org.springframework.stereotype.Component
import team.comit.simtong.domain.file.model.EmployeeCertificate
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.file.entity.EmployeeCertificateJpaEntity

/**
 *
 * EmployeeCertificate Entity와 Employee Certificate Aggregate를 변환하는 EmployeeCertificateMapper
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/12/06
 * @version 1.2.5
 **/
@Component
class EmployeeCertificateMapper : GenericMapper<EmployeeCertificateJpaEntity, EmployeeCertificate> {

    override fun toEntity(model: EmployeeCertificate): EmployeeCertificateJpaEntity {
        return EmployeeCertificateJpaEntity(
            employeeNumber = model.employeeNumber,
            name = model.name,
            spotName = model.spotName,
            teamName = model.teamName
        )
    }

    override fun toDomain(entity: EmployeeCertificateJpaEntity?): EmployeeCertificate? {
        return entity?.let {
            EmployeeCertificate(
                employeeNumber = it.employeeNumber,
                name = it.name,
                spotName = it.spotName,
                teamName = it.teamName
            )
        }
    }

    override fun toDomainNotNull(entity: EmployeeCertificateJpaEntity): EmployeeCertificate {
        return EmployeeCertificate(
            employeeNumber = entity.employeeNumber,
            name = entity.name,
            spotName = entity.spotName,
            teamName = entity.teamName
        )
    }
}