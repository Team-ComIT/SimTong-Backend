package team.comit.simtong.domain.user.policy

import team.comit.simtong.domain.auth.exception.UncertifiedEmailException
import team.comit.simtong.domain.auth.exception.UsedEmailException
import team.comit.simtong.domain.spot.exception.SpotNotFoundException
import team.comit.simtong.domain.spot.spi.DomainQuerySpotPort
import team.comit.simtong.domain.team.exception.TeamNotFoundException
import team.comit.simtong.domain.team.spi.DomainQueryTeamPort
import team.comit.simtong.domain.user.dto.DomainSignUpRequest
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserQueryAuthCodeLimitPort
import team.comit.simtong.domain.user.spi.UserQuerySpotPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.Policy

/**
 *
 * 회원 가입 정책을 관리하는 SignUpPolicy
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/05
 * @version 1.0.0
 **/
@Policy
class SignUpPolicy(
//    private val nickNamePort: NickNamePort,
    private val domainQueryTeamPort: DomainQueryTeamPort,
    private val userQuerySpotPort: UserQuerySpotPort,
    private val userQueryAuthCodeLimitPort: UserQueryAuthCodeLimitPort,
    private val queryUserPort: QueryUserPort,
    private val userSecurityPort: UserSecurityPort
) {

    fun implement(request: DomainSignUpRequest): User {
        val authCodeLimit = userQueryAuthCodeLimitPort.queryAuthCodeLimitByEmail(request.email)

        if (authCodeLimit == null || !authCodeLimit.isVerified) {
            throw UncertifiedEmailException.EXCEPTION
        }

        if (queryUserPort.existsUserByEmail(request.email)) {
            throw UsedEmailException.EXCEPTION
        }

        // TODO 비즈니스 로직 직접 구현
        // 임직원 확인

        val spot = userQuerySpotPort.querySpotByName("test spotName") // 임직원 확인시 지점 이름 가져오기
            ?: throw SpotNotFoundException.EXCEPTION

        val team = domainQueryTeamPort.queryTeamByName("test teamName") // 임직원 확인시 팀 이름 가져오기
            ?: throw TeamNotFoundException.EXCEPTION

        return User(
            name = request.name,
            email = request.email,
            password = userSecurityPort.encode(request.password),
            nickname = request.nickname ?: "", // nickNamePort.random()
            employeeNumber = request.employeeNumber,
            authority = Authority.ROLE_COMMON,
            spotId = spot.id,
            teamId = team.id,
            profileImagePath = request.profileImagePath ?: User.defaultImage
        )
    }
}