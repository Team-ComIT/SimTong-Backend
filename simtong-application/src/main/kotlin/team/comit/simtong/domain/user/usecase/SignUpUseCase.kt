package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.auth.spi.ReceiveTokenPort
import team.comit.simtong.domain.auth.usecase.dto.TokenResponse
import team.comit.simtong.domain.user.dto.SignUpRequest
import team.comit.simtong.domain.user.policy.SignUpPolicy
import team.comit.simtong.domain.user.spi.SaveUserPort
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 사용자의 회원가입 기능을 담당하는 SignUpUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/09/04
 * @version 1.0.0
 **/
@UseCase
class SignUpUseCase(
    private val receiveTokenPort: ReceiveTokenPort,
    private val saveUserPort: SaveUserPort,
    private val signUpPolicy: SignUpPolicy
) {

    fun execute(request: SignUpRequest): TokenResponse {
        val user = saveUserPort.saveUser(signUpPolicy.implement(request))

        return receiveTokenPort.generateJsonWebToken(
            userId = user.id!!,
            authority = user.authority
        )
    }

}