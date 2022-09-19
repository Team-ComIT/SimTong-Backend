package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.auth.usecase.dto.TokenResponse
import team.comit.simtong.domain.user.policy.SignUpPolicy
import team.comit.simtong.domain.user.spi.CommandUserPort
import team.comit.simtong.domain.user.spi.UserJwtPort
import team.comit.simtong.domain.user.usecase.dto.SignUpRequest
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 사용자의 회원가입 기능을 담당하는 SignUpUseCase
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/04
 * @version 1.0.0
 **/
@UseCase
class SignUpUseCase(
    private val userJwtPort: UserJwtPort,
    private val commandUserPort: CommandUserPort,
    private val signUpPolicy: SignUpPolicy
) {

    fun execute(request: SignUpRequest): TokenResponse {
        val user = commandUserPort.save(
            signUpPolicy.implement(
                nickname = request.nickname,
                name = request.name,
                email = request.email,
                password = request.password,
                employeeNumber = request.employeeNumber,
                profileImagePath = request.profileImagePath
            )
        )

        return userJwtPort.receiveToken(
            userId = user.id,
            authority = user.authority
        )
    }

}