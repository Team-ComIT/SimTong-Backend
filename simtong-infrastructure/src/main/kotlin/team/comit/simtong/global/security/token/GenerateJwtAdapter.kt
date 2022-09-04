package team.comit.simtong.global.security.token

import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import team.comit.simtong.domain.auth.spi.ReceiveTokenPort
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.global.security.SecurityProperties
import team.comit.simtong.persistence.auth.RefreshTokenRepository
import team.comit.simtong.persistence.auth.entity.RefreshTokenEntity
import java.util.*

/**
 *
 * Access 토큰과 Refresh 토큰을 생성하는 GenerateJwtAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/09/01
 * @version 1.0.0
 **/
@Component
class GenerateJwtAdapter(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val securityProperties: SecurityProperties
) : ReceiveTokenPort {

    override fun generateAccessToken(userId: UUID, authority: Authority): String {
        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS512, securityProperties.encodingSecretKey)
            .setHeaderParam(Header.JWT_TYPE, JwtComponent.ACCESS)
            .setId(userId.toString())
            .claim(JwtComponent.AUTHORITY, authority)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + securityProperties.accessExpiredTime))
            .compact()
    }

    override fun generateRefreshToken(userId: UUID, authority: Authority): String {
        val token = Jwts.builder()
            .signWith(SignatureAlgorithm.HS512, securityProperties.encodingSecretKey)
            .setHeaderParam(Header.JWT_TYPE, JwtComponent.REFRESH)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + securityProperties.refreshExpiredTime))
            .compact()

        refreshTokenRepository.save(RefreshTokenEntity(
            token = token,
            authority = authority,
            userId = userId,
            expirationTime = securityProperties.refreshExpiredTime
        ))

        return token
    }
}