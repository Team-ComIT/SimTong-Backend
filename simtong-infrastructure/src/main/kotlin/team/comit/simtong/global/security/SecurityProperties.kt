package team.comit.simtong.global.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.util.*

/**
 *
 * security와 관련된 환경변수를 불러오는 SecurityProperties
 *
 * @author kimbeomjin
 * @date 2022/08/31
 * @version 1.0.0
 **/
@ConfigurationProperties("spring.security")
@ConstructorBinding
class SecurityProperties(
    secretKey: String,
    accessExp: Int,
    val refreshExp: Int
) {
    val accessExpiredTime = accessExp * 1000L
    val refreshExpiredTime = refreshExp * 1000L
    val encodingSecretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())!!
}