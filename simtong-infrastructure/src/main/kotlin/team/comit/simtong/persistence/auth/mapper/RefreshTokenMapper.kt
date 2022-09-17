package team.comit.simtong.persistence.auth.mapper

import org.mapstruct.Mapper
import team.comit.simtong.domain.auth.model.RefreshToken
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.auth.entity.RefreshTokenEntity

/**
 *
 * RefreshTokenEntity와 DomainRefreshToken을 변환하는 RefreshTokenMapper
 *
 * @author Chokyunghyeon
 * @date 2022/09/18
 * @version 1.0.0
 **/
@Mapper
abstract class RefreshTokenMapper : GenericMapper<RefreshTokenEntity, RefreshToken> {
}