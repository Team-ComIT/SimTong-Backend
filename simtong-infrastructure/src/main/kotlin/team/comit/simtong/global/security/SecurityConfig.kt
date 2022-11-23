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
import team.comit.simtong.domain.user.model.Authority.ROLE_ADMIN
import team.comit.simtong.domain.user.model.Authority.ROLE_COMMON
import team.comit.simtong.domain.user.model.Authority.ROLE_SUPER
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

            // users
            .antMatchers(HttpMethod.POST, "/users").permitAll()
            .antMatchers(HttpMethod.POST, "/users/tokens").permitAll()
            .antMatchers(HttpMethod.GET, "/users/information").hasRole(ROLE_COMMON.role)
            .antMatchers(HttpMethod.GET, "/users/nickname/duplication").hasRole(ROLE_COMMON.role)
            .antMatchers(HttpMethod.PUT, "/users/profile-image").hasRole(ROLE_COMMON.role)
            .antMatchers(HttpMethod.PUT, "/users/nickname").hasRole(ROLE_COMMON.role)
            .antMatchers(HttpMethod.PUT, "/users/email").hasRole(ROLE_COMMON.role)
            .antMatchers(HttpMethod.PUT, "/users/profile-image").hasRole(ROLE_COMMON.role)
            .antMatchers(HttpMethod.PUT, "/users/spot").hasRole(ROLE_COMMON.role)

            // commons
            .antMatchers(HttpMethod.GET, "/commons/account/existence").permitAll()
            .antMatchers(HttpMethod.PUT, "/commons/email/duplication").permitAll()
            .antMatchers(HttpMethod.GET, "/commons/employee-number").permitAll()
            .antMatchers(HttpMethod.PUT, "/commons/token/reissue").permitAll()
            .antMatchers(HttpMethod.PUT, "/commons/password/initialization").permitAll()
            .antMatchers(HttpMethod.GET, "/commons/spot").permitAll()

            // emails
            .antMatchers(HttpMethod.GET, "/emails").permitAll()
            .antMatchers(HttpMethod.POST, "/emails/code").permitAll()

            // menu
            .antMatchers(HttpMethod.GET, "/menu/public").permitAll()

            // files
            .antMatchers(HttpMethod.POST, "/files").permitAll()
            .antMatchers(HttpMethod.POST, "/files/list").permitAll()

            // schedules
            .antMatchers(HttpMethod.POST, "/schedules/spots/{spot-id}").hasAnyRole(ROLE_ADMIN.role, ROLE_SUPER.role)
            .antMatchers(HttpMethod.PUT, "/schedules/spots/{schedule-id}").hasAnyRole(ROLE_ADMIN.role, ROLE_SUPER.role)

            // admins
            .antMatchers(HttpMethod.POST, "/admins/tokens").permitAll()

            .anyRequest().authenticated()

        http
            .apply(FilterConfig(objectMapper, jwtParser))

        return http.build()
    }

    @Bean
    protected fun PasswordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}