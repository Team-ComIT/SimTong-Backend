package team.comit.simtong.persistence.auth.mapper

import org.springframework.stereotype.Component
import team.comit.simtong.domain.auth.model.RefreshToken
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.auth.entity.RefreshTokenEntity

/**
 *
 * RefreshTokenEntity와 DomainRefreshToken을 변환하는 RefreshTokenMapper
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/18
 * @version 1.2.5
 **/
@Component
class RefreshTokenMapper : GenericMapper<RefreshTokenEntity, RefreshToken> {

    override fun toEntity(model: RefreshToken): RefreshTokenEntity {
        return RefreshTokenEntity(
            token = model.token,
            userId = model.userId,
            authority = model.authority,
            expirationTime = model.expirationTime
        )
    }

    override fun toDomain(entity: RefreshTokenEntity?): RefreshToken? {
        return entity?.let {
            RefreshToken(
                token = it.token,
                userId = it.userId,
                authority = it.authority,
                expirationTime = it.expirationTime
            )
        }
    }

    override fun toDomainNotNull(entity: RefreshTokenEntity): RefreshToken {
        return RefreshToken(
            token = entity.token,
            userId = entity.userId,
            authority = entity.authority,
            expirationTime = entity.expirationTime
        )
    }
}