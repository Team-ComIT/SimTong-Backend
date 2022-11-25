package team.comit.simtong.global.config

import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 *
 * LocalDateTime, LocalDate, LocalTime 요청값을 ISO-8601으로 설정하는 DateFormatConfig
 *
 * @author Chokyunghyeon
 * @date 2022/11/26
 * @version 1.0.0
 **/
@Configuration
class DateFormatConfig : WebMvcConfigurer {

    override fun addFormatters(registry: FormatterRegistry) {
        val registrar = DateTimeFormatterRegistrar().apply {
            setUseIsoFormat(true)
        }
        registrar.registerFormatters(registry)
    }
}