package team.comit.simtong.global.security.token

import io.jsonwebtoken.*
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import team.comit.simtong.global.exception.InternalServerErrorException
import team.comit.simtong.global.security.SecurityProperties
import team.comit.simtong.global.security.exception.UnexpectedTokenException
import team.comit.simtong.global.security.exception.ExpiredTokenException
import team.comit.simtong.global.security.exception.InvalidTokenException
import team.comit.simtong.global.security.exception.WrongTypeTokenException

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
    private val securityProperties: SecurityProperties
) {

    private fun getClaims(token: String): Jws<Claims>? {
        return try {
            Jwts.parser()
                .setSigningKey(securityProperties.encodingSecretKey)
                .parseClaimsJws(token)
        } catch (e: Exception) {
            when(e) {
                is InvalidClaimException -> InvalidTokenException.EXCEPTION
                is ExpiredJwtException -> ExpiredTokenException.EXCEPTION
                is JwtException -> UnexpectedTokenException.EXCEPTION
                else -> InternalServerErrorException.EXCEPTION
            }
            null
        }
    }

    fun getAuthentication(token: String): Authentication? {
        val claims = getClaims(token)

        claims?.let {

            if (claims.header[Header.JWT_TYPE] != JwtComponent.ACCESS) {
                WrongTypeTokenException.EXCEPTION
            }

        }

         TODO("authDetail 로직 구현")
    }
}