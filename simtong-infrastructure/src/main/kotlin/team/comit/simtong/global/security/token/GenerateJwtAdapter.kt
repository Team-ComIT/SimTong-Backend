package team.comit.simtong.global.security.token

import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import team.comit.simtong.domain.auth.spi.ReceiveTokenPort
import team.comit.simtong.domain.auth.usecase.dto.TokenResponse
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.global.security.SecurityProperties
import team.comit.simtong.persistence.auth.repository.RefreshTokenRepository
import team.comit.simtong.persistence.auth.entity.RefreshTokenEntity
import java.util.UUID
import java.util.Date

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

    private fun generateAccessToken(userId: UUID, authority: Authority): String {
        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS512, securityProperties.encodingSecretKey)
            .setHeaderParam(Header.JWT_TYPE, JwtComponent.ACCESS)
            .setId(userId.toString())
            .claim(JwtComponent.AUTHORITY, authority)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + securityProperties.accessExpiredTime))
            .compact()
    }

    private fun generateRefreshToken(userId: UUID, authority: Authority): String {
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
            expirationTime = securityProperties.refreshExpiredTime / 1000
        ))

        return token
    }

    override fun generateJsonWebToken(userId: UUID, authority: Authority): TokenResponse {
        return TokenResponse(
            accessToken = generateAccessToken(userId, authority),
            refreshToken = generateRefreshToken(userId, authority),
            accessTokenExp = Date(System.currentTimeMillis() + securityProperties.accessExpiredTime)
        )
    }

}