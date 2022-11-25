package team.comit.simtong.global.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import team.comit.simtong.global.DomainProperties

/**
 *
 * Domain Aggregate의 설정을 불러오는 DomainConfig
 *
 * @author Chokyunghyeon
 * @date 2022/11/25
 * @version 1.0.0
 **/
@ConstructorBinding
@ConfigurationProperties("domain")
class DomainPropertiesConfig(
    model: Map<String, String>
) {
    init {
        DomainProperties.putAll(model)
    }
}