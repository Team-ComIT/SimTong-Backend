package team.comit.simtong.global.filter

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import team.comit.simtong.global.security.token.JwtComponent
import team.comit.simtong.global.security.token.JwtParser
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
  *
  * Jwt를 검사해 권한을 인증하는 JwtFilter
  *
  * @author JoKyungHyeon
  * @date 2022/09/03
  * @version 1.0.0
 **/
class JwtFilter(
    private val jwtParser: JwtParser
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = resolvedToken(request)

        token?.run {
            SecurityContextHolder.getContext().authentication = jwtParser.getAuthentication(this)
        }

        filterChain.doFilter(request, response)
    }

    private fun resolvedToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(JwtComponent.HEADER)

        if(bearerToken.isNotEmpty().and(bearerToken.startsWith(JwtComponent.PREFIX))) {
            return bearerToken.substring(7)
        }

        return null
    }
}