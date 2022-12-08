package team.comit.simtong.global.config

import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 *
 * WebMvc 관련 설정을 하는 WebMvcConfig
 * LocalDateTime, LocalDate, LocalTime 요청값을 ISO-8601으로 설정
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/12/08
 * @version 1.0.0
 **/
@Configuration
class WebMvcConfig : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedHeaders("*")
            .allowedMethods("*")
            .allowedOrigins("*")
    }

    override fun addFormatters(registry: FormatterRegistry) {
        val registrar = DateTimeFormatterRegistrar().apply {
            setUseIsoFormat(true)
        }
        registrar.registerFormatters(registry)
    }
}