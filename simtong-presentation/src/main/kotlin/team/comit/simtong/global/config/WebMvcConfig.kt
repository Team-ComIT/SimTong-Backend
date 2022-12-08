package team.comit.simtong.global.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 *
 * Cors 관련 설정을 하는 WebMvcConfig
 *
 * @author Chokyunghyeon
 * @date 2022/12/08
 * @version 1.0.0
 **/
@Configuration
class WebMvcConfig : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedMethods("*")
            .allowedOriginPatterns("*")
    }
}