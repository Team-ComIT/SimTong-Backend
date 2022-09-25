package team.comit.simtong.domain.auth.usecase

import team.comit.simtong.domain.auth.policy.SendAuthCodePolicy
import team.comit.simtong.domain.auth.spi.CommandAuthCodeLimitPort
import team.comit.simtong.domain.auth.spi.CommandAuthCodePort
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
    private val sendAuthCodePolicy: SendAuthCodePolicy,
    private val commandAuthCodeLimitPort: CommandAuthCodeLimitPort,
    private val commandAuthCodePort: CommandAuthCodePort,
    private val sendEmailPort: SendEmailPort
) {

    fun execute(email: String) {
        commandAuthCodeLimitPort.save(
            sendAuthCodePolicy.restriction(email)
        )

        val authCode = commandAuthCodePort.save(
            sendAuthCodePolicy.implement(email)
        )

        sendEmailPort.sendAuthCode(authCode.code, email)
    }

}