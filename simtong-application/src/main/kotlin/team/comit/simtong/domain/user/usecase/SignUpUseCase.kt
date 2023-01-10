package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.auth.dto.TokenResponse
import team.comit.simtong.domain.auth.exception.AuthExceptions
import team.comit.simtong.domain.file.exception.FileExceptions
import team.comit.simtong.domain.spot.exception.SpotExceptions
import team.comit.simtong.domain.team.exception.TeamExceptions
import team.comit.simtong.domain.user.dto.SignUpRequest
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.model.value.Authority
import team.comit.simtong.domain.user.model.DeviceToken
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.CommandDeviceTokenPort
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
 * @version 1.2.1
 **/
@UseCase
class SignUpUseCase(
    private val jwtPort: UserJwtPort,
    private val commandUserPort: CommandUserPort,
    private val commandDeviceTokenPort: CommandDeviceTokenPort,
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

        when {
            queryUserPort.existsUserByEmail(email) ->
                throw AuthExceptions.AlreadyUsedEmail()

            queryUserPort.existsUserByEmployeeNumber(employeeNumber) ->
                throw AuthExceptions.AlreadyUsedEmployeeNumber()

            queryUserPort.existsUserByNickname(nickname) ->
                throw UserExceptions.AlreadyUsedNickname()
        }

        val authCodeLimit = queryAuthCodeLimitPort.queryAuthCodeLimitByEmail(email)
            ?: throw AuthExceptions.RequiredNewEmailAuthentication()

        if (!authCodeLimit.verified) {
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
                nickname = nickname,
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

        commandDeviceTokenPort.save(
            DeviceToken(
                userId = user.id,
                token = request.deviceToken
            )
        )

        return jwtPort.receiveToken(
            userId = user.id,
            authority = user.authority
        )
    }

}