package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.auth.dto.TokenResponse
import team.comit.simtong.domain.auth.exception.AuthExceptions
import team.comit.simtong.domain.file.exception.FileExceptions
import team.comit.simtong.domain.spot.exception.SpotExceptions
import team.comit.simtong.domain.team.exception.TeamExceptions
import team.comit.simtong.domain.user.dto.SignUpRequest
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.CommandUserPort
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserCommandAuthCodeLimitPort
import team.comit.simtong.domain.user.spi.UserJwtPort
import team.comit.simtong.domain.user.spi.UserQueryAuthCodeLimitPort
import team.comit.simtong.domain.user.spi.UserQueryEmployeeCertificatePort
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
    private val securityPort: UserSecurityPort,
    private val queryEmployeeCertificatePort: UserQueryEmployeeCertificatePort
) {

    fun execute(request: SignUpRequest): TokenResponse {
        val (name, email, password, nickname, employeeNumber, profileImagePath) = request

        val authCodeLimit = queryAuthCodeLimitPort.queryAuthCodeLimitByEmail(email)
            ?: throw AuthExceptions.RequiredNewEmailAuthentication()

        when {
            queryUserPort.existsUserByEmail(email) ->
                throw AuthExceptions.AlreadyUsedEmail()

            queryUserPort.existsUserByEmployeeNumber(employeeNumber) ->
                throw AuthExceptions.AlreadyUsedEmployeeNumber()

            !authCodeLimit.verified -> 
                throw AuthExceptions.UncertifiedEmail()
        }

        val employeeCertificate = queryEmployeeCertificatePort.queryEmployeeCertificateByNameAndEmployeeNumber(name, employeeNumber)
            ?: throw FileExceptions.NotExistsEmployee()

        val spot = querySpotPort.querySpotByName(employeeCertificate.spotName)
            ?: throw SpotExceptions.NotFound()

        val team = queryTeamPort.queryTeamByName(employeeCertificate.teamName)
            ?: throw TeamExceptions.NotFound()

        val user = commandUserPort.save(
            User(
                nickname = nickname ?: "", // TODO 랜덤 닉네임
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