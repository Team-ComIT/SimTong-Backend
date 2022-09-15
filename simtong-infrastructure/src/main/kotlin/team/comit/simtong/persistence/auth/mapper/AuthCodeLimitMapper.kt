package team.comit.simtong.persistence.auth.mapper

import org.mapstruct.Mapper
import team.comit.simtong.domain.auth.model.AuthCodeLimit
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.auth.entity.AuthCodeLimitEntity

/**
 *
 * AuthCodeLimitEntity와 DomainAuthCodeLimit을 변환하는 AuthCodeLimitMapper
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/11
 * @version 1.0.0
 **/
@Mapper
abstract class AuthCodeLimitMapper : GenericMapper<AuthCodeLimitEntity, AuthCodeLimit> {

    abstract fun toDomain(entity: AuthCodeLimitEntity?): AuthCodeLimit?

    abstract fun toEntity(domain: AuthCodeLimit?): AuthCodeLimitEntity?
}