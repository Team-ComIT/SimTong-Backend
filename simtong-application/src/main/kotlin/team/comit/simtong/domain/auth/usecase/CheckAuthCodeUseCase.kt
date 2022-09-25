package team.comit.simtong.domain.auth.usecase

import team.comit.simtong.domain.auth.policy.CheckAuthCodePolicy
import team.comit.simtong.domain.auth.spi.CommandAuthCodeLimitPort
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 이메일 인증 코드 확인을 담당하는 CheckAuthCodeUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/09/24
 * @version 1.0.0
 **/
@UseCase
class CheckAuthCodeUseCase(
    private val checkAuthCodePolicy: CheckAuthCodePolicy,
    private val commandAuthCodeLimitPort: CommandAuthCodeLimitPort
) {

    fun execute(email: String, code: String) {
        commandAuthCodeLimitPort.save(
            checkAuthCodePolicy.implement(email, code)
        )
    }

}