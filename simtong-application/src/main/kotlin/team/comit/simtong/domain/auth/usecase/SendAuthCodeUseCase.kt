package team.comit.simtong.domain.auth.usecase

import team.comit.simtong.domain.auth.exception.CertifiedEmailException
import team.comit.simtong.domain.auth.model.AuthCode
import team.comit.simtong.domain.auth.model.AuthCodeLimit
import team.comit.simtong.domain.auth.spi.CommandAuthCodeLimitPort
import team.comit.simtong.domain.auth.spi.CommandAuthCodePort
import team.comit.simtong.domain.auth.spi.QueryAuthCodeLimitPort
import team.comit.simtong.domain.auth.spi.SendEmailPort
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 이메일 인증 코드 전송을 담당하는 SendAuthCodeUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/09/24
 * @version 1.0.0
 **/
@UseCase
class SendAuthCodeUseCase(
    private val commandAuthCodeLimitPort: CommandAuthCodeLimitPort,
    private val commandAuthCodePort: CommandAuthCodePort,
    private val queryAuthCodeLimitPort: QueryAuthCodeLimitPort,
    private val sendEmailPort: SendEmailPort
) {

    fun execute(email: String) {
        val authCodeLimit = queryAuthCodeLimitPort.queryAuthCodeLimitByEmail(email)
            ?: AuthCodeLimit(email)

        if(authCodeLimit.isVerified) {
            throw CertifiedEmailException.EXCEPTION
        }

        commandAuthCodeLimitPort.save(authCodeLimit.increaseCount())

        val authCode = commandAuthCodePort.save(AuthCode(email))

        sendEmailPort.sendAuthCode(authCode.code, email)
    }

}