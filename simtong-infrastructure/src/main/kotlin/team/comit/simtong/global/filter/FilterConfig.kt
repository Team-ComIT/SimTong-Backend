package team.comit.simtong.global.filter

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component
import team.comit.simtong.global.security.token.JwtParser

/**
 *
 * SecurityFilterChain에 직접 만든 Filter를 등록하는 FilterConfig
 *
 * @author kimbeomjin
 * @date 2022/08/31
 * @version 1.0.0
 **/
@Component
class FilterConfig(
    private val objectMapper: ObjectMapper,
    private val jwtParser: JwtParser
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(builder: HttpSecurity) {
        builder.addFilterBefore(JwtFilter(jwtParser), UsernamePasswordAuthenticationFilter::class.java)
        builder.addFilterBefore(ExceptionFilter(objectMapper), JwtFilter::class.java)
    }
}