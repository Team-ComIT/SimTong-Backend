package team.comit.simtong.global.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

/**
 *
 * ConfigurationProperties 어노테이션을 활성화하기 위한 ConfigurationPropertiesConfig
 *
 * @author kimbeomjin
 * @date 2022/08/31
 * @version 1.0.0
 **/
@ConfigurationPropertiesScan("team.comit.simtong")
@Configuration
class ConfigurationPropertiesConfig