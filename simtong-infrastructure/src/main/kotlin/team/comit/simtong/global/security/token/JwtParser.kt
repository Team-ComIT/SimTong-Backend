package team.comit.simtong.global.security.token

import io.jsonwebtoken.*
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import team.comit.simtong.global.security.SecurityProperties

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

    private fun getClaims(token: String): Jws<Claims> {
        try {
            return Jwts.parser()
                .setSigningKey(securityProperties.encodingSecretKey)
                .parseClaimsJws(token)
        } catch (e: Exception) {
            // TODO Exception 설정
            when(e) {
                is InvalidClaimException -> throw Exception()
                is ExpiredJwtException -> throw Exception()
                is JwtException -> throw Exception()
                is IllegalArgumentException -> throw Exception()
                else -> throw Exception()
            }
        }
    }

    fun getAuthentication(token: String): Authentication? {
        val claims = getClaims(token)

        if (claims.header[Header.JWT_TYPE] != JwtComponent.JWT_ACCESS) {
            throw Exception() // TODO Exception 설정
        }

         TODO("authDetail 로직 구현")
    }
}