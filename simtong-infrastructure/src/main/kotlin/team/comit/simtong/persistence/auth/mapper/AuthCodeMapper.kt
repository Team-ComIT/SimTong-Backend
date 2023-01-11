package team.comit.simtong.persistence.auth.mapper

import org.springframework.stereotype.Component
import team.comit.simtong.domain.auth.model.AuthCode
import team.comit.simtong.domain.auth.model.value.Code
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.auth.entity.AuthCodeEntity

/**
 *
 * AuthCodeEntity와 DomainAuthCode를 변환하는 AuthCodeMapper
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/25
 * @version 1.2.5
 **/
@Component
class AuthCodeMapper : GenericMapper<AuthCodeEntity, AuthCode> {

    override fun toEntity(model: AuthCode): AuthCodeEntity {
        return AuthCodeEntity(
            key = model.key,
            code = model.code.value,
            expirationTime = model.expirationTime
        )
    }

    override fun toDomain(entity: AuthCodeEntity?): AuthCode? {
        return entity?.let {
            AuthCode.of(
                key = it.key,
                code = Code.of(it.code),
                expirationTime = it.expirationTime
            )
        }
    }

    override fun toDomainNotNull(entity: AuthCodeEntity): AuthCode {
        return AuthCode.of(
            key = entity.key,
            code = Code.of(entity.code),
            expirationTime = entity.expirationTime
        )
    }
}