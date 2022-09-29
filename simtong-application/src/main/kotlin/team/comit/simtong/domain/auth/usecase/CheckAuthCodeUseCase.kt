package team.comit.simtong.domain.auth.usecase

import team.comit.simtong.domain.auth.exception.AuthCodeMismatchException
import team.comit.simtong.domain.auth.model.AuthCodeLimit
import team.comit.simtong.domain.auth.spi.CommandAuthCodeLimitPort
import team.comit.simtong.domain.auth.spi.QueryAuthCodePort
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
    private val commandAuthCodeLimitPort: CommandAuthCodeLimitPort,
    private val queryAuthCodePort: QueryAuthCodePort
) {

    fun execute(email: String, code: String) {
        val authCode = queryAuthCodePort.queryAuthCodeByEmail(email)

        if (authCode?.code != code) {
            throw AuthCodeMismatchException.EXCEPTION
        }

        commandAuthCodeLimitPort.save(
            AuthCodeLimit.verified(email)
        )
    }

}