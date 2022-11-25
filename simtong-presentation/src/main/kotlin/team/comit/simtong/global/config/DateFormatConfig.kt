package team.comit.simtong.global.config

import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class DateFormatConfig : WebMvcConfigurer {

    override fun addFormatters(registry: FormatterRegistry) {
        val registrar = DateTimeFormatterRegistrar().apply {
            setUseIsoFormat(true)
        }
        registrar.registerFormatters(registry)
    }
}