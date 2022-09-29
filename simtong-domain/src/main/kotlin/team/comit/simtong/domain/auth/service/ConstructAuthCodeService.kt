package team.comit.simtong.domain.auth.service

import net.bytebuddy.utility.RandomString
import team.comit.simtong.domain.auth.model.AuthCode
import team.comit.simtong.global.annotation.DomainService

/**
 *
 * AuthCode를 생성하는 ConstructAuthCodeService
 *
 * @author Chokyunghyeon
 * @date 2022/09/29
 * @version 1.0.0
 **/
@DomainService
class ConstructAuthCodeService {

    fun construct(email: String): AuthCode {
        return AuthCode(
            key = email,
            code = RandomString(6).nextString(),
            expirationTime = AuthCode.EXPIRED
        )
    }

}