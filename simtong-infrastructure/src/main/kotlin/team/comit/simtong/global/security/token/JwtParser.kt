package team.comit.simtong.global.security.token

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Header
import io.jsonwebtoken.InvalidClaimException
import io.jsonwebtoken.Jws
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import team.comit.simtong.global.exception.GlobalExceptions
import team.comit.simtong.global.security.SecurityProperties
import team.comit.simtong.global.security.exception.SecurityExceptions
import team.comit.simtong.global.security.principle.AuthDetailsService

/**
 *
 * Jwt 토큰의 정보를 얻는 JwtParser
 *
 * @author Chokyunghyeon
 * @date 2022/09/01
 * @version 1.0.0
 **/
@Component
class JwtParser(
    private val securityProperties: SecurityProperties,
    private val authDetailsService: AuthDetailsService
) {

    private fun getClaims(token: String): Jws<Claims> {
        return try {
            Jwts.parser()
                .setSigningKey(securityProperties.encodingSecretKey)
                .parseClaimsJws(token)
        } catch (e: Exception) {
            when (e) {
                is InvalidClaimException -> throw SecurityExceptions.InvalidToken()
                is ExpiredJwtException -> throw SecurityExceptions.ExpiredToken()
                is JwtException -> throw SecurityExceptions.UnexpectedToken()
                else -> throw GlobalExceptions.InternalServerError()
            }
        }
    }

    fun getAuthentication(token: String): Authentication {
        val claims = getClaims(token)

        if (claims.header[Header.JWT_TYPE] != JwtComponent.ACCESS) {
            throw SecurityExceptions.WrongTypeToken("Access Token이어야 합니다.")
        }

        val details = authDetailsService.loadUserByUsername(claims.body.id)

        return UsernamePasswordAuthenticationToken(details, "", details.authorities)
    }
}