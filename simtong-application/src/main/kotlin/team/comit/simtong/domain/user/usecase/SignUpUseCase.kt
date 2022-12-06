package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.auth.dto.TokenResponse
import team.comit.simtong.domain.auth.exception.RequiredNewEmailAuthenticationException
import team.comit.simtong.domain.auth.exception.UncertifiedEmailException
import team.comit.simtong.domain.auth.exception.UsedEmailException
import team.comit.simtong.domain.spot.exception.SpotNotFoundException
import team.comit.simtong.domain.team.exception.TeamNotFoundException
import team.comit.simtong.domain.user.dto.SignUpRequest
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.CommandUserPort
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserCommandAuthCodeLimitPort
import team.comit.simtong.domain.user.spi.UserJwtPort
import team.comit.simtong.domain.user.spi.UserQueryAuthCodeLimitPort
import team.comit.simtong.domain.user.spi.UserQuerySpotPort
import team.comit.simtong.domain.user.spi.UserQueryTeamPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
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
    private val jwtPort: UserJwtPort,
    private val commandUserPort: CommandUserPort,
    private val queryUserPort: QueryUserPort,
    private val queryAuthCodeLimitPort: UserQueryAuthCodeLimitPort,
    private val commandAuthCodeLimitPort: UserCommandAuthCodeLimitPort,
    private val querySpotPort: UserQuerySpotPort,
    private val queryTeamPort: UserQueryTeamPort,
    //    private val nickNamePort: NickNamePort,
    private val securityPort: UserSecurityPort
) {

    fun execute(request: SignUpRequest): TokenResponse {
        val (name, email, password, nickname, employeeNumber, profileImagePath) = request

        if (queryUserPort.existsUserByEmail(email)) {
            throw UsedEmailException.EXCEPTION
        }

        val authCodeLimit = queryAuthCodeLimitPort.queryAuthCodeLimitByEmail(email)
            ?: throw RequiredNewEmailAuthenticationException.EXCEPTION

        if (!authCodeLimit.verified) {
            throw UncertifiedEmailException.EXCEPTION
        }

        // TODO 비즈니스 로직 직접 구현
        // 임직원 확인

        val spot = querySpotPort.querySpotByName("test spotName")
            ?: throw SpotNotFoundException.EXCEPTION

        val team = queryTeamPort.queryTeamByName("test teamName")
            ?: throw TeamNotFoundException.EXCEPTION

        val user = commandUserPort.save(
            User(
                nickname = nickname ?: "", // nickNamePort.random()
                name = name,
                email = email,
                password = securityPort.encode(password),
                employeeNumber = employeeNumber,
                authority = Authority.ROLE_COMMON,
                spotId = spot.id,
                teamId = team.id,
                profileImagePath = profileImagePath ?: User.DEFAULT_IMAGE
            )
        )

        commandAuthCodeLimitPort.delete(authCodeLimit)

        return jwtPort.receiveToken(
            userId = user.id,
            authority = user.authority
        )
    }

}