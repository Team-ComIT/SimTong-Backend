package team.comit.simtong.persistence.auth.mapper

import org.springframework.stereotype.Component
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
 * @version 1.2.5
 **/
@Component
class AuthCodeLimitMapper : GenericMapper<AuthCodeLimitEntity, AuthCodeLimit> {

    override fun toEntity(model: AuthCodeLimit): AuthCodeLimitEntity {
        return AuthCodeLimitEntity(
            key = model.key,
            expirationTime = model.expirationTime,
            attemptCount = model.attemptCount,
            verified = model.verified
        )
    }

    override fun toDomain(entity: AuthCodeLimitEntity?): AuthCodeLimit? {
        return entity?.let {
            AuthCodeLimit(
                key = it.key,
                expirationTime = it.expirationTime,
                attemptCount = it.attemptCount,
                verified = it.verified
            )
        }
    }

    override fun toDomainNotNull(entity: AuthCodeLimitEntity): AuthCodeLimit {
        return AuthCodeLimit(
            key = entity.key,
            expirationTime = entity.expirationTime,
            attemptCount = entity.attemptCount,
            verified = entity.verified
        )
    }
}