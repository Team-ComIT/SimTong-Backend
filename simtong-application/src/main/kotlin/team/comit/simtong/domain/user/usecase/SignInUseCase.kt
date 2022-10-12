package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.auth.dto.TokenResponse
import team.comit.simtong.domain.user.dto.SignInRequest
import team.comit.simtong.domain.user.exception.DifferentPasswordException
import team.comit.simtong.domain.user.exception.NotUserAccountException
import team.comit.simtong.domain.user.exception.UserNotFoundException
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserJwtPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 사용자의 로그인 기능을 담당하는 SignInUseCase
 *
 * @author kimbeomjin
 * @date 2022/09/08
 * @version 1.0.0
 **/
@UseCase
class SignInUseCase(
    private val queryUserPort: QueryUserPort,
    private val userSecurityPort: UserSecurityPort,
    private val userJwtPort: UserJwtPort
) {

    fun execute(request: SignInRequest): TokenResponse {
        val user = queryUserPort.queryUserByEmployeeNumber(request.employeeNumber)
            ?: throw UserNotFoundException.EXCEPTION

        if(user.authority != Authority.ROLE_COMMON) {
            throw NotUserAccountException.EXCEPTION
        }

        if (!userSecurityPort.compare(request.password, user.password)) {
            throw DifferentPasswordException.EXCEPTION
        }

        return userJwtPort.receiveToken(
            userId = user.id,
            authority = Authority.ROLE_COMMON
        )
    }

}