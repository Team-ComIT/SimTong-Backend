package team.comit.simtong.thirdparty.email

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

/**
 *
 * SES와 관련된 환경변수를 가져오는 AwsSESProperties
 *
 * @author Chokyunghyeon
 * @date 2022/09/08
 * @version 1.0.0
 **/
@ConfigurationProperties("cloud.aws.ses")
@ConstructorBinding
class AwsSESProperties(
    val source: String
)