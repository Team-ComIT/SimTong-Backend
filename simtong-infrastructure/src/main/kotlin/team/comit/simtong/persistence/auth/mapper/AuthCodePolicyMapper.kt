package team.comit.simtong.persistence.auth.mapper

import org.mapstruct.Mapper
import team.comit.simtong.domain.auth.model.AuthCodePolicy
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.auth.entity.AuthCodePolicyEntity

/**
 *
 * AuthCodePolicyEntity와 DomainAuthCodePolicy를 변환하는 AuthCodePolicyMapper
 *
 * @author Chokyunghyeon
 * @date 2022/09/11
 * @version 1.0.0
 **/
@Mapper
abstract class AuthCodePolicyMapper : GenericMapper<AuthCodePolicyEntity, AuthCodePolicy> {

    abstract fun toDomain(entity: AuthCodePolicyEntity?): AuthCodePolicy?

    abstract fun toEntity(domain: AuthCodePolicy?): AuthCodePolicyEntity?
}