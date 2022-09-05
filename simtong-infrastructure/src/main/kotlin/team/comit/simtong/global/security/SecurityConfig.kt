package team.comit.simtong.global.security

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import team.comit.simtong.global.filter.FilterConfig
import team.comit.simtong.global.security.token.JwtParser

/**
 *
 * Spring Security와 관련된 설정을 관리하는 SecurityConfig
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/08/31
 * @version 1.0.0
 **/
@Configuration
class SecurityConfig(
    private val objectMapper: ObjectMapper,
    private val jwtParser: JwtParser
) {

    @Bean
    protected fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors().and()
            .csrf().disable()
            .formLogin().disable()

        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/users").permitAll()
            .anyRequest().authenticated()

        http
            .apply(FilterConfig(objectMapper, jwtParser))

        return http.build()
    }

    @Bean
    protected fun PasswordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}