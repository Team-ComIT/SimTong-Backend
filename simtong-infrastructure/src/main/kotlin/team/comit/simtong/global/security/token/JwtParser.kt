package team.comit.simtong.global.security.token

import io.jsonwebtoken.*
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import team.comit.simtong.global.exception.InternalServerErrorException
import team.comit.simtong.global.security.SecurityProperties
import team.comit.simtong.global.security.exception.UnexpectedTokenException
import team.comit.simtong.global.security.exception.ExpiredTokenException
import team.comit.simtong.global.security.exception.InvalidTokenException
import team.comit.simtong.global.security.exception.WrongTypeTokenException
import team.comit.simtong.global.security.principle.AuthDetailsService

/**
  *
  * Jwt 토큰의 정보를 얻는 JwtParser
  *
  * @author JoKyungHyeon
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
            when(e) {
                is InvalidClaimException -> throw InvalidTokenException.EXCEPTION
                is ExpiredJwtException -> throw ExpiredTokenException.EXCEPTION
                is JwtException -> throw UnexpectedTokenException.EXCEPTION
                else -> throw InternalServerErrorException.EXCEPTION
            }
        }
    }

    fun getAuthentication(token: String): Authentication {
        val claims = getClaims(token)

        if (claims.header[Header.JWT_TYPE] != JwtComponent.ACCESS) {
            throw WrongTypeTokenException.EXCEPTION
        }

        val details = authDetailsService.loadUserByUsername(claims.body.id)

        return UsernamePasswordAuthenticationToken(details, "", details.authorities)
    }
}